package cn.netinnet.usercenter.service.impl;

import cn.netinnet.cloudcommon.constant.CacheConstant;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.constant.ParaConstant;
import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.globol.ResultEnum;
import cn.netinnet.cloudcommon.utils.JWTUtil;
import cn.netinnet.cloudcommon.utils.UserUtil;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.Md5EncryptUtil;
import cn.netinnet.usercenter.dao.SysUserMapper;
import cn.netinnet.usercenter.domain.SysUser;
import cn.netinnet.usercenter.feign.NinZuulClient;
import cn.netinnet.usercenter.service.SysLoginLogService;
import cn.netinnet.usercenter.service.SysUserService;
import cn.netinnet.usercenter.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
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
    SysLoginLogService sysLoginLogService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    NinZuulClient ninZuulClient;

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
        UserInfo userInfo = new UserInfo(user.getUserId(), userLogin, user.getUserName(), user.getUserType(), user.getRoleCode());
        return HttpResultEntry.ok("登录成功", userInfoSet(userInfo, true, ip));
    }

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
            // 进行登录日志记录
            sysLoginLogService.addLoginLogByAsync(userInfo, ip);
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
                //schoolStatus = sysSchoolMapper.querySchoolStatusById(schoolId);
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


    @Override
    public int updateByPrimaryKeySelective(SysUser sysUser, long l) {
        return 0;
    }

    @Override
    public Class getClazz() {
        return null;
    }
}
