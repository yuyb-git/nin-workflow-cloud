package cn.netinnet.educationcenter.service;

import cn.netinnet.cloudcommon.constant.UserConstant;
import cn.netinnet.cloudcommon.constant.RoleConstant;
import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.common.base.Service;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.educationcenter.domain.SysUser;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yuyb
 * @date   2020-04-03
 */
public interface SysUserService extends Service<SysUser> {

    static SysUser genSysUser(SysUser sysUser, UserInfo userInfo, String userLogin,
                              String userName, Integer sex, String phone, String passwordMd5){
        SysUser user = new SysUser();
        user.setUserId(DateUtil.getUID());
        user.setUserType(UserConstant.STUDENT);
        user.setRoleCode(RoleConstant.STUDENT);
        user.setUserStatus(UserConstant.STATUS_ACTIVE);
        user.setUserLogin(userLogin);
        user.setUserName(userName);
        user.setUserPwd(passwordMd5);
        user.setUserSex(sex);
        user.setUserPhone(phone);
        user.setSchoolId(userInfo.getSchoolId());
        user.setSchoolName(userInfo.getSchoolName());
        user.setClassId(sysUser.getClassId());
        user.setClassName(sysUser.getClassName());
        user.setCollegeId(sysUser.getCollegeId());
        user.setCollegeName(sysUser.getCollegeName());
        user.setMajorId(sysUser.getMajorId());
        user.setMajorName(sysUser.getMajorName());
        user.setCreateUserId(userInfo.getUserId());
        user.setModifyUserId(userInfo.getUserId());
        return user;
    }

    /** 查询用户 **/
    List<SysUser> queryList(SysUser sysUser);

    /** 登录 **/
    HttpResultEntry login(String info, String ip);

    /** 修改密码 **/
    HttpResultEntry changePwd(String oldPwd, String newPwd);

    /** 用户新增 **/
    HttpResultEntry add(SysUser user, Integer userType);

    /** 用户编辑 **/
    HttpResultEntry edit(SysUser user, Integer userType);

    /** 批量逻辑删除用户（一个/多个） **/
    HttpResultEntry logicDeleteByIdList(List<Long> userIdList);

    /** 学生导入 **/
    HttpResultEntry importStudent(SysUser sysUser, JSONArray list);

    List<Map<String, Object>> getUserGroupByClass(Long schoolId, String className, List<Long> studentIdList, String projectCode, Long userId);

    /** 查询用户名 **/
    Map<Long, String> queryNameMap(Set<Long> userIds);

    Map<String, Object> userInfoSet(UserInfo userInfo, boolean doLog, String ip);
}
