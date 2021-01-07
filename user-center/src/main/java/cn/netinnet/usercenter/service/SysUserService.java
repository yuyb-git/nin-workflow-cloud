package cn.netinnet.usercenter.service;

import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.common.base.Service;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.usercenter.domain.SysUser;
import com.alibaba.fastjson.JSONArray;
import com.auth0.jwt.interfaces.Claim;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

}
