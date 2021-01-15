package cn.netinnet.logcenter.service.impl;

import cn.netinnet.cloudcommon.base.BaseService;
import cn.netinnet.cloudcommon.exception.CustomException;
import cn.netinnet.cloudcommon.utils.DateUtil;
import cn.netinnet.logcenter.dao.SysLogMapper;
import cn.netinnet.logcenter.domain.SysLog;
import cn.netinnet.logcenter.service.SysLogService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author yuyb
 * @date   2020-04-07
 */
@Service
public class SysLogServiceImpl extends BaseService<SysLog> implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public int insertSelective(SysLog sysLog, long userId) {
        return sysLogMapper.insertSelective(sysLog);
    }
    @Override
    public Class getClazz(){
        return SysLog.class;
    }

    @Async
    @Override
    public void addSysLogByAsync(Integer userType, Long userId, String userName, String operationName, String requestUrl, String requestParams) {
        SysLog sysLog = new SysLog();
        sysLog.setLogId(DateUtil.getUID());
        sysLog.setUserType(userType);
        sysLog.setUserId(userId);
        sysLog.setUserName(userName);
        sysLog.setOperationName(operationName);
        sysLog.setRequestUrl(requestUrl);
        sysLog.setRequestParams(requestParams);
        sysLog.setSpendTime(10L);
        sysLogMapper.insertSelective(sysLog);
    }

    /**
     * @Author Linjj
     * @Date 2019/8/8 9:55
     * @Description 异步新增系统日志
     */
    @Async
    @Override
    public void addSysLogByAsync(SysLog sysLog) {
        sysLogMapper.insertSelective(sysLog);
    }

    /** 方法描述
     * @description 查询系统日志
     * @param beginDate
     * @param endDate
     * @param userName
     * @return [beginDate, endDate, userName]
     * @author wanghy
     * @date 2020/4/9 9:39
     */
    @Override
    public List<SysLog> queryList(String beginDate, String endDate, String requestUrl, String userName) {
        // 查询系统日志
        Map<String, Object> params = new HashMap<>(4);
        try {
            if (StringUtils.isNotBlank(beginDate)) {
                params.put("beginDate", DateUtils.parseDate(beginDate, "yyyy-MM-dd"));
            }
            if (StringUtils.isNotBlank(endDate)) {
                // 结束日期+1天，判断时才能包含截止当天的记录
                params.put("endDate", DateUtils.addDays(DateUtils.parseDate(endDate, "yyyy-MM-dd"), 1));
            }
        } catch (Exception e) {
            throw new CustomException("日期解析异常");
        }
        if (StringUtils.isNotBlank(userName)) {
            params.put("userName", "%" + userName + "%");
        }
        if(StringUtils.isNotBlank(requestUrl)){
            params.put("requestUrl", requestUrl);
        }
        return sysLogMapper.queryList(params);
    }
}