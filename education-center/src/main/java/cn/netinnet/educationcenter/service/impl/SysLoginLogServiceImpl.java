package cn.netinnet.educationcenter.service.impl;

import cn.netinnet.cloudcommon.dto.UserInfo;
import cn.netinnet.common.base.BaseService;
import cn.netinnet.common.util.DateUtil;
import cn.netinnet.educationcenter.dao.SysLoginLogMapper;
import cn.netinnet.educationcenter.domain.SysLoginLog;
import cn.netinnet.educationcenter.service.SysLoginLogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @author yuyb
 * @date   2020-06-19
 */
@Service
public class SysLoginLogServiceImpl extends BaseService<SysLoginLog> implements SysLoginLogService {
    @Resource
    private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public int updateByPrimaryKeySelective(SysLoginLog sysLoginLog, long l) {
        return 0;
    }

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
    public void addLoginLogByAsync(UserInfo userInfo, String ip) {
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setLogId(DateUtil.getUID());
        loginLog.setUserId(userInfo.getUserId());
        loginLog.setUserName(userInfo.getUserName());
        loginLog.setSchoolId(userInfo.getSchoolId());
        loginLog.setLoginTime(new Date());
        loginLog.setLoginIp(ip);
        // 记录日志
        sysLoginLogMapper.insertSelective(loginLog);
    }

}
