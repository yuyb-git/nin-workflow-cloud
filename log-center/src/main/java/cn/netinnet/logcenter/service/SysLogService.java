package cn.netinnet.logcenter.service;


import cn.netinnet.common.base.Service;
import cn.netinnet.logcenter.domain.SysLog;

import java.util.List;


/**
 * @author yuyb
 * @date   2020-04-07
 */
public interface SysLogService  extends Service<SysLog> {

    /** 异步新增系统日志 **/
    void addSysLogByAsync(Integer userType, Long userId, String userName, String operationName, String requestUrl, String requestParams);

    /** 异步新增系统日志 **/
    void addSysLogByAsync(SysLog sysLog);

    /** 查询系统日志 **/
    List<SysLog> queryList(String beginDate, String endDate, String requestUrl, String userName);
}
