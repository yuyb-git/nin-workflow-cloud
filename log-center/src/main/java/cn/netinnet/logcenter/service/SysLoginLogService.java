package cn.netinnet.logcenter.service;


import cn.netinnet.common.base.Service;
import cn.netinnet.logcenter.domain.SysLoginLog;

/**
 * @author yuyb
 * @date   2020-06-19
 */
public interface SysLoginLogService extends Service<SysLoginLog> {

    /** 异步新增登录日志 **/
    void addLoginLogByAsync(SysLoginLog loginLog);

}
