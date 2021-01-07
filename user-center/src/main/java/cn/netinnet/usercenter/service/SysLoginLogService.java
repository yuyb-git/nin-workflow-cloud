package cn.netinnet.usercenter.service;

import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.common.base.Service;
import cn.netinnet.usercenter.domain.SysLoginLog;

/**
 * @author yuyb
 * @date   2020-06-19
 */
public interface SysLoginLogService extends Service<SysLoginLog> {

    /** 异步新增登录日志 **/
    void addLoginLogByAsync(UserInfo userInfo, String ip);

}
