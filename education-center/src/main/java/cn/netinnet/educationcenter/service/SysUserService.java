package cn.netinnet.educationcenter.service;

import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.common.base.Service;
import cn.netinnet.educationcenter.domain.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author yuyb
 * @date   2020-04-03
 */
public interface SysUserService extends Service<SysUser> {

    /** 查询用户 **/
    List<SysUser> queryList(SysUser sysUser);

    /** 登录 **/
    HttpResultEntry login(String info, String ip);

    /** 修改密码 **/
    HttpResultEntry changePwd(String oldPwd, String newPwd);

    List<Map<String, Object>> getUserGroupByClass(Long schoolId, String className, List<Long> studentIdList, String projectCode, Long userId);
}
