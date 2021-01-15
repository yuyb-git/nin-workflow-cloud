package cn.netinnet.logcenter.service.impl;

import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.logcenter.dao.SysLoginLogMapper;
import cn.netinnet.logcenter.domain.SysLoginLog;
import cn.netinnet.logcenter.service.SysLoginLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author yuyb
 * @date   2020-06-19
 */
@Service
public class SysLoginLogServiceImpl extends BaseService<SysLoginLog> implements SysLoginLogService {

    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public int insertSelective(SysLoginLog sysLoginLog, long userId) {
        return sysLoginLogMapper.insertSelective(sysLoginLog);
    }
    @Override
    public Class getClazz(){
        return SysLoginLog.class;
    }

    /**
     * @Author Linjj
     * @Date 2019/11/15 15:13
     * @Description 异步新增登录日志
     */
    @Async
    @Override
    public void addLoginLogByAsync(SysLoginLog loginLog) {
        // 记录日志
        sysLoginLogMapper.insertSelective(loginLog);
    }

}