package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.constant.*;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.kafka.KafkaProducer;
import cn.netinnet.cloudcommon.utils.*;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.common.util.Md5EncryptUtil;
import cn.netinnet.common.util.StringUtil;
import cn.netinnet.educationcenter.dao.SysBatchStudentMapper;
import cn.netinnet.educationcenter.dao.SysSchoolMapper;
import cn.netinnet.educationcenter.dao.SysUserMapper;
import cn.netinnet.educationcenter.domain.SysUser;
import cn.netinnet.educationcenter.feign.NinZuulClient;
import cn.netinnet.educationcenter.service.SysUserService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author yuyb
 * @date   2020-04-03
 */
@Service
public class SysUserServiceImpl extends BaseService<SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    NinZuulClient ninZuulClient;
    @Resource
    SysSchoolMapper sysSchoolMapper;
    @Resource
    private SysBatchStudentMapper sysBatchStudentMapper;
    @Resource
    private KafkaProducer kafkaProducer;
    @Value("${kafka.topic_name_producer}")
    private String topicName;

    @Override
    public List<SysUser> queryList (SysUser sysUser) {
        return sysUserMapper.queryList(sysUser);
    }

    @Override
    public HttpResultEntry login(String info, String ip) {
        //因为前端所传参数用base64进行加密加密后的字符串的头尾各加3个随机字母或数字，所以我们只能固定长度字符串进行截取就好
        String base64CutStr = info.substring(3, info.length() - 3);
        //进行解码
        Base64.Decoder decoder = Base64.getDecoder();
        JSONObject loginObject = JSONObject.parseObject(new String(decoder.decode(base64CutStr)));
        if (!loginObject.containsKey("userLogin") || !loginObject.containsKey("userPwd") || !loginObject.containsKey("type")) {
            return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        String userLogin = loginObject.getString("userLogin").trim();
        String userPwd = loginObject.getString("userPwd").trim();
        Integer type = loginObject.getInteger("type");
        // 查找用户
        SysUser user = sysUserMapper.queryUserByLogin(userLogin);
        // 进行登录校验，校验不通过则直接返回校验信息
        HttpResultEntry checkResult = loginCheck(user, userPwd, type);
        if (checkResult.getCode() != GlobalConstant.SUCCESS) {
            return checkResult;
        }
        UserInfo userInfo = new UserInfo(user.getUserId(), user.getSchoolId(), userLogin, user.getUserName(), user.getUserType(), user.getRoleCode());
        return HttpResultEntry.ok("登录成功", userInfoSet(userInfo, true, ip));
    }

    @Override
    public Map<String, Object> userInfoSet(UserInfo userInfo, boolean doLog, String ip) {

        // 生成JWT的accessToken和refreshToken
        long currentTime = System.currentTimeMillis();
        Date accessExpiresAt = new Date(currentTime + JWTUtil.EXPIRE_TIME);
        String accessToken = JWTUtil.sign(userInfo, accessExpiresAt);
        Date refreshExpiresAt = new Date(currentTime + JWTUtil.REFRESH_TIME);
        String refreshToken = JWTUtil.sign(userInfo, refreshExpiresAt);
        // 将userId -> refreshToken信息存入redis，用于标记userId对应的token状态，可判断token是否已失效
        String cacheKey = RedisUtil.getKey(CacheConstant.R_USERID_TOKEN, userInfo.getUserId());
        RedisUtil.set(cacheKey, refreshToken);
        // 设置过期时间
        RedisUtil.expireAt(cacheKey, refreshExpiresAt);
        //将token->refreshToken信息存入redis中
        String tokenCacheKey= RedisUtil.getKey(CacheConstant.R_TOKEN_REFRSHTOKEN, accessToken);
        RedisUtil.set(tokenCacheKey, refreshToken);
        //设置正常token的过期时间
        RedisUtil.expireAt(tokenCacheKey, accessExpiresAt);
        // 返回加密两次的userId串，截取9-24位，返回key为"k"，用于区分同一浏览器多用户登录问题
        String beforeUserId = String.valueOf(userInfo.getUserId());
        String encryUserId = Md5EncryptUtil.getMD5Str(Md5EncryptUtil.getMD5Str(beforeUserId)).substring(8, 24);
        if (doLog) {
            JSONObject data = new JSONObject();
            data.put("msgType", 0);
            data.put("userId", userInfo.getUserId());
            data.put("userName", userInfo.getUserName());
            data.put("schoolId", userInfo.getSchoolId());
            data.put("ip", ip);
            kafkaProducer.sendMsg(topicName, data);
        }
        // 获取用户对应的权限集
        List<String> rolePermission = ninZuulClient.getPermissionByRoleCode(userInfo.getRoleCode());
        Set<String> permissionSet = rolePermission.stream()
                // 过滤空字符的权限
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());

        // 返回token和过期时间
        Map<String, Object> result = new HashMap<>();
        result.put("token", accessToken);
        result.put("refreshToken", refreshToken);
        result.put("expiresAt", accessExpiresAt);
        result.put("userInfo", userInfo);
        result.put("k", encryUserId);
        result.put("permissionSet", permissionSet);
        return result;
    }

    private HttpResultEntry loginCheck(SysUser user, String userPwd, Integer type) {
        if (user == null) {
            return HttpResultEntry.customize(ResultEnum.R_USER_NOT_EXIST);
        }
        // 校验用户类型
        int userType = user.getUserType();
        switch (type) {
            case UserConstant.LOGIN_TYPE_ADMIN:
                if (userType != UserConstant.ADMIN && userType != UserConstant.MANAGER && userType != UserConstant.TEACHER) {
                    return HttpResultEntry.customize(ResultEnum.R_USER_NOT_EXIST);
                }
                break;
            case UserConstant.LOGIN_TYPE_STUDENT:
                if (userType != UserConstant.STUDENT) {
                    return HttpResultEntry.customize(ResultEnum.R_USER_NOT_EXIST);
                }
                break;
            default:
                return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        // 校验用户密码
        if (!userPwd.equals(user.getUserPwd())) {
            return HttpResultEntry.customize(ResultEnum.R_USER_ERR);
        }
        // 校验用户账号状态
        if (user.getUserStatus() == UserConstant.STATUS_FORBIDDEN) {
            return HttpResultEntry.customize(ResultEnum.R_USER_FORBIDDEN);
        }
        // 校验学校状态（如果有学校信息，即id大于0）
        Long schoolId = user.getSchoolId();
        if (schoolId != null && schoolId > 0L) {
            String schoolIdKey = RedisUtil.getKey(CacheConstant.R_SCHOOL_STATUS, schoolId);
            Integer schoolStatus = null;
            if (RedisUtil.hasKey(schoolIdKey)) {
                schoolStatus = (Integer) RedisUtil.get(schoolIdKey);
            } else {
                schoolStatus = sysSchoolMapper.querySchoolStatusById(schoolId);
                RedisUtil.set(schoolIdKey, null == schoolStatus ? ParaConstant.SCHOOL_STATUS_FORBIDDEN : schoolStatus);
            }
            if (schoolStatus == null) {
                return HttpResultEntry.customize(ResultEnum.R_SCHOOL_NOT_EXSITS);
            }
            if (ParaConstant.SCHOOL_STATUS_FORBIDDEN == schoolStatus) {
                return HttpResultEntry.customize(ResultEnum.R_SCHOOL_FORBIDDEN);
            }
        }
        return HttpResultEntry.ok();
    }

    /** 方法描述
     * @description 修改密码
     * @param oldPwd
     * @param newPwd
     * @return [oldPwd, newPwd]
     * @author wanghy
     * @date 2020/4/7 15:37
     */
    @Override
    public HttpResultEntry changePwd(String oldPwd, String newPwd) {
        String oldPasswordMd5 = Md5EncryptUtil.getMD5Str(oldPwd);
        String newPasswordMd5 = Md5EncryptUtil.getMD5Str(newPwd);
        //错误旧密码检查
        Long userId = UserUtil.getUser().getUserId();
        String oriPwd = sysUserMapper.queryPwdById(userId);
        if (!oriPwd.equals(oldPasswordMd5)) {
            return HttpResultEntry.error(GlobalConstant.FAILURE, "旧密码输入错误，请重试");
        }
        sysUserMapper.updatePwdById(userId, newPasswordMd5);
        return HttpResultEntry.ok();
    }

    /** 方法描述
     * @description 用户新增
     * @param user
     * @param userType
     * @return [user, userType]
     * @author wanghy
     * @date 2020/4/8 9:33
     */
    @Override
    public HttpResultEntry add(SysUser user, Integer userType) {
        UserInfo userInfo = UserUtil.getUser();
        // 进行login校验，判断login是否已被占用
        int loginCount = sysUserMapper.countNumByCondition(user.getUserLogin(), null);
        // 账号已存在
        if (loginCount > 0) {
            return HttpResultEntry.customize(ResultEnum.R_EXIST_LOGIN);
        }
        switch (userType) {
            // 系统管理员
            case UserConstant.ADMIN:
                user.setUserType(UserConstant.ADMIN);
                user.setRoleCode(RoleConstant.ADMIN);
                break;
            // 学校管理员
            case UserConstant.MANAGER:
                user.setUserType(UserConstant.MANAGER);
                user.setRoleCode(RoleConstant.MANAGER);
                break;
            // 教师
            case UserConstant.TEACHER:
                if (user.getCreateClassPermission() == 0) {
                    // 无创建行政班权限的教师角色
                    user.setRoleCode(RoleConstant.TEACHER_NOCLASS);
                } else {
                    // 普通教师角色
                    user.setRoleCode(RoleConstant.TEACHER);
                }
                user.setUserType(UserConstant.TEACHER);
                user.setSchoolId(userInfo.getSchoolId());
                user.setSchoolName(userInfo.getSchoolName());
                break;
            // 学生
            case UserConstant.STUDENT:
                user.setUserType(UserConstant.STUDENT);
                user.setRoleCode(RoleConstant.STUDENT);
                user.setSchoolId(userInfo.getSchoolId());
                user.setSchoolName(userInfo.getSchoolName());
                break;
            default:
                return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        Long userId = DateUtil.getUID();
        // 进行密码MD5加密
        String pwd = org.apache.commons.lang3.StringUtils.isBlank(user.getUserPwd()) ? UserConstant.DEFAULT_PASSWORD : user.getUserPwd();
        String pwdMd5 = Md5EncryptUtil.getMD5Str(pwd);
        user.setUserId(userId);
        user.setUserStatus(UserConstant.STATUS_ACTIVE);
        user.setUserPwd(pwdMd5);
        user.setCreateUserId(userInfo.getUserId());
        user.setModifyUserId(userInfo.getUserId());
        // 进行用户生成
        sysUserMapper.insertSelective(user);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG, userId);
    }

    /** 方法描述
     * @description 用户编辑
     * @param user
     * @param userType
     * @return [user, userType]
     * @author wanghy
     * @date 2020/4/8 9:34
     */
    @Override
    public HttpResultEntry edit(SysUser user, Integer userType) {
        //UserInfo userInfo = UserUtil.getUser();
        SysUser updateUser = new SysUser();
        switch (userType) {
            // 系统管理员特有部分（角色编码）
            case UserConstant.ADMIN:
                updateUser.setRoleCode(user.getRoleCode());
                break;
            // 学校管理员特有部分（）
            case UserConstant.MANAGER:
                // todo 编辑学校管理员
                break;
            // 教师特有部分（学院/专业/是否可创建班级）
            case UserConstant.TEACHER:
                if (user.getCreateClassPermission() == 0) {
                    // 无创建行政班权限的教师角色
                    updateUser.setRoleCode(RoleConstant.TEACHER_NOCLASS);
                } else {
                    // 普通教师角色
                    updateUser.setRoleCode(RoleConstant.TEACHER);
                }
                updateUser.setCollegeId(user.getCollegeId());
                updateUser.setCollegeName(user.getCollegeName());
                updateUser.setMajorId(user.getMajorId());
                updateUser.setMajorName(user.getMajorName());
                updateUser.setCreateClassPermission(user.getCreateClassPermission());
                break;
            // 学生特有部分（学院/专业/班级/学年）
            case UserConstant.STUDENT:
                // 学年
                String userGrade = user.getUserGrade();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(userGrade) && userGrade.length() != 4) {
                    return HttpResultEntry.error("学年长度必须为4位");
                }
                updateUser.setCollegeId(user.getCollegeId());
                updateUser.setCollegeName(user.getCollegeName());
                updateUser.setMajorId(user.getMajorId());
                updateUser.setMajorName(user.getMajorName());
                updateUser.setClassId(user.getClassId());
                updateUser.setClassName(user.getClassName());
                updateUser.setUserGrade(userGrade);
                break;
            default:
                return HttpResultEntry.customize(ResultEnum.R_PARAM);
        }
        updateUser.setUserId(user.getUserId());
        updateUser.setUserLogin(user.getUserLogin());
        updateUser.setUserName(user.getUserName());
        updateUser.setUserSex(user.getUserSex());
        if (org.apache.commons.lang3.StringUtils.isNotBlank(user.getUserPwd()) && !"******".equals(user.getUserPwd())) {
            updateUser.setUserPwd(Md5EncryptUtil.getMD5Str(user.getUserPwd()));
        }
        updateUser.setUserPhone(user.getUserPhone());
        updateUser.setUserMail(user.getUserMail());
        updateUser.setUserNickname(user.getUserNickname());
        updateUser.setUserBirthday(user.getUserBirthday());
        sysUserMapper.updateByPrimaryKeySelective(updateUser);
        // 清除对应用户的权限缓存
        String userCacheKey = RedisUtil.getKey(CacheConstant.R_SHIRO_CACHE, user.getUserId());
        RedisUtil.del(userCacheKey);
        return HttpResultEntry.ok(GlobalConstant.SUCCESS_MSG);
    }

    /** 方法描述
     * @description 批量逻辑删除用户（一个/多个）
     * @param userIdList
     * @return [userIdList]
     * @author wanghy
     * @date 2020/4/8 9:36
     */
    @Override
    public HttpResultEntry logicDeleteByIdList(List<Long> userIdList) {
        long modifyUserId = UserUtil.getUser().getUserId();
        Date now = new Date();
        List<SysUser> updateList = new ArrayList<>();
        for (Long userId : userIdList) {
            SysUser updateUser = new SysUser();
            updateUser.setUserId(userId);
            updateUser.setDelFlag(1);
            updateUser.setModifyUserId(modifyUserId);
            updateUser.setModifyTime(now);
            updateList.add(updateUser);
        }
        // 批量逻辑删除
        sysUserMapper.batchUpdateByPrimaryKey(updateList);
        // 批次下的学生要一起删除
        sysBatchStudentMapper.logicDeleteByIdUserId(userIdList);
        return HttpResultEntry.ok("删除成功");
    }

    /** 方法描述
     * @description 学生导入
     * @param sysUser
     * @param list
     * @return [sysUser, list]
     * @author wanghy
     * @date 2020/4/8 9:55
     */
    @Override
    public HttpResultEntry importStudent(SysUser sysUser, JSONArray list) {
        UserInfo userInfo = UserUtil.getUser();
        // 进行学生信息校验
        List<String> errList = checkStudentImportInfo(list);
        // 校验无错误信息，则导入学生
        if (!CollectionUtils.isEmpty(errList)){
            return HttpResultEntry.customize(ResultEnum.R_IMPORT_CHECK, errList);
        }
        String name, login, password, passwordMd5, phone;
        List<SysUser> importUserList = new ArrayList<>();
        JSONObject student;
        SysUser importUser;
        for (int i = 0; i < list.size(); i++) {
            student = list.getJSONObject(i);
            name = student.getString("userName");
            login = student.getString("login");
            password = org.apache.commons.lang3.StringUtils.isBlank(student.getString("password")) ? UserConstant.DEFAULT_PASSWORD : student.getString("password");
            passwordMd5 = Md5EncryptUtil.getMD5Str(password);
            Integer sex = 2;
            if (student.get("sex") != null) {
                sex = student.getInteger("sex");
            }
            phone = student.getString("phone");
            // 生成学生
            importUser = SysUserService.genSysUser(sysUser, userInfo, login, name, sex, phone, passwordMd5);
            importUserList.add(importUser);
        }
        sysUserMapper.batchInsertSelective(importUserList);
        return HttpResultEntry.ok("上传成功");
    }

    /** 方法描述
     * @description 校验导入的学生信息，返回校验信息
     * @param list
     * @return [list]
     * @author wanghy
     * @date 2020/4/8 9:56
     */
    private List<String> checkStudentImportInfo(JSONArray list){
        // 校验错误信息列表
        List<String> errList = new ArrayList<>();
        // 姓名map，校验是否重复
        Map<String, String> nameMap = new HashMap<>(16);
        // 姓名重复错误信息
        List<String> nameRepeat = new ArrayList<>();
        // 账号map，校验是否重复
        Map<String, String> loginMap = new HashMap<>(16);
        // 账号重复错误信息
        List<String> loginRepeat = new ArrayList<>();
        // 校验账号是否已存在
        List<String> loginList = new ArrayList<>();
        StringBuilder err;
        JSONObject student;
        String rows, name, login, password, phone;
        for (int i = 0; i < list.size(); i++) {
            err = new StringBuilder();
            student = list.getJSONObject(i);
            rows = student.getString("rows");
            name = student.getString("userName");
            login = student.getString("login");
            password = student.getString("password");
            phone = student.getString("phone");
            // 校验信息
            if (StringUtil.isBlankOrNull(name)) {
                err.append("第").append(rows).append("行 第B列").append(ErrorMsgConstant.USER_NAME_NULL);
            }
            if (name.length() > 15) {
                err.append("第").append(rows).append("行 第B列").append(ErrorMsgConstant.USER_NAME_LENGTH);
            }
            if (org.apache.commons.lang3.StringUtils.isBlank(login)) {
                err.append("第").append(rows).append("行 第E列").append(ErrorMsgConstant.USER_LOGIN_NULL);
            }
            if (login.length() > 30 || login.length() < 1) {
                err.append("第").append(rows).append("行 第E列").append(ErrorMsgConstant.USER_LOGIN_LENGTH);
            }
            if (RegUtil.isNotMatch(RegUtil.REGEX_LETTER_NUM, login)) {
                err.append("第").append(rows).append("行 第E列").append(ErrorMsgConstant.USER_LOGIN_CHAR);
            }
            if (password.length() > 16 || password.length() < 6) {
                err.append("第").append(rows).append("行 第F列").append(ErrorMsgConstant.USER_PWD_LENGTH);
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(phone) && phone.length() != 11) {
                err.append("第").append(rows).append("行 第D列").append(ErrorMsgConstant.USER_PHONE_LENGTH);
            }
            // 添加错误信息
            if (err.length() > 0) {
                errList.add(err.toString());
            }
            // 名称判断是否重复
            if (!nameMap.containsKey(name)) {
                nameMap.put(name, rows);
            } else {
                if (!nameRepeat.contains(name)) {
                    nameRepeat.add(name);
                }
                // 拼接重复的行次
                nameMap.put(name, nameMap.get(name) + "," + rows);
            }
            // 账号判断是否重复
            CommonUtil.judgeLoginRepeat(loginMap, loginList, loginRepeat, login, rows);
        }
        // 如果存在重复，添加重复信息
        if (!nameRepeat.isEmpty()) {
            CommonUtil.genErrInfo(nameMap, nameRepeat, errList, "B", "信息重复");
        }
        if (!loginRepeat.isEmpty()) {
            CommonUtil.genErrInfo(loginMap, loginRepeat, errList, "E", "信息重复");
        }
        // 判断账号是否已存在
        if (!loginList.isEmpty()){
            List<String> existList = sysUserMapper.checkLoginsExist(loginList);
            if (!existList.isEmpty()) {
                CommonUtil.genErrInfo(loginMap, existList, errList, "E", "系统已经存在");
            }
        }
        return errList;
    }

    @Override
    public int updateByPrimaryKeySelective(SysUser sysUser, long l) {
        return 0;
    }

    @Override
    public Class getClazz() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getUserGroupByClass(Long schoolId, String className, List<Long> studentIdList, String projectCode, Long userId) {
        if(!StringUtil.isBlankOrNull(className)){
            className = "%"+className+"%";
        }
        //排除已存在的考试学生
        List<SysUser> userInfoList;
        if (ParaConstant.PROJECT_CODE_DIF.equals(projectCode)) {
            userInfoList = sysUserMapper.queryUserInfo(schoolId, className, studentIdList, userId);
        } else {
            userInfoList = sysUserMapper.queryUserInfo(schoolId, className, studentIdList,null);
        }
        Map<Long, List<SysUser>> userMap = userInfoList.stream().collect(Collectors.groupingBy(SysUser::getClassId));
        List<Map<String, Object>> listMap = new ArrayList<>();
        for (Long classId : userMap.keySet()) {
            Map<String, Object> classMap = new HashMap<>(16);
            classMap.put("classId", classId);
            classMap.put("className", userMap.get(classId).get(0).getClassName());
            classMap.put("userList", userMap.get(classId));
            listMap.add(classMap);
        }
        return listMap;
    }

    /***
     * 查询用户名
     * @param userIds    用户id
     * @author ousp
     * @date 2020/8/26
     * @return java.util.Map<java.lang.Long,java.lang.String>
     */
    @Override
    public Map<Long, String> queryNameMap(Set<Long> userIds) {
        Map<Long, String> nameMap = new HashMap<>();
        sysUserMapper.queryUserName(userIds).forEach(u -> nameMap.put(u.getUserId(), u.getUserName()));
        return nameMap;
    }
}
