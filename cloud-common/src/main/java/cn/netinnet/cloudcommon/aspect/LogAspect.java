package cn.netinnet.cloudcommon.aspect;

import cn.netinnet.cloudcommon.annotation.LogMark;
import cn.netinnet.cloudcommon.constant.GlobalConstant;
import cn.netinnet.cloudcommon.dto.LogInfo;
import cn.netinnet.cloudcommon.globol.HttpResultEntry;
import cn.netinnet.cloudcommon.kafka.KafkaProducer;
import cn.netinnet.cloudcommon.utils.HttpContextUtil;
import com.alibaba.fastjson.JSON;
import groovy.util.logging.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 \* @Author: Linjj
 \* @Date: 2019/7/31 16:54
 \* @Description: 系统日记记录切面
 \*/
@Aspect
@Component
@Slf4j
@Order(3)
public class LogAspect {

    // 是否开启系统日志，false-关闭，true-开启, 默认开启
    @Value("${app.isOpenLog:true}")
    private boolean isOpenLog;
    @Value("${kafka.topic_name_producer}")
    private String topicName;

    @Resource
    private KafkaProducer kafkaProducer;

    /**
     * @Author Linjj
     * @Date 2019/7/31 17:03
     * @Description 编织切面切入点
     */
    @Pointcut("@annotation(cn.netinnet.cloudcommon.annotation.LogMark)")
    public void pointcut() {
    }

    /**
     * @Author Linjj
     * @Date 2019/7/31 17:03
     * @Description 环绕目标代码周围，记录日志信息
     */
    @Around("pointcut() && @annotation(logMark)")
    public Object around(ProceedingJoinPoint point, LogMark logMark) throws Throwable {
        // 记录执行方法前的时间戳
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 若开启日志，则进行日志记录
        if (isOpenLog) {
            // 用于暂时存储操作
            String resultMsg = "(成功)";
            if (result instanceof HttpResultEntry) {
                HttpResultEntry resultEntry = (HttpResultEntry) result;
                // 若返回的结果code非成功，则直接返回结果，不记录本次日志
                //if (resultEntry.getCode() != GlobalConstant.SUCCESS) {
                //    return result;
                //}
                // todo 不管成功或失败都记录，方便调试测试，把成功或失败的信息记录到操作信息里
                if (resultEntry.getCode() != GlobalConstant.SUCCESS) {
                    resultMsg = "(失败)";
                }
            }
            HttpServletRequest request = HttpContextUtil.getRequest();
            // 设置 IP地址
            String ip = "";
            // 计算执行时长(毫秒)
            long spendTime = System.currentTimeMillis() - beginTime;
            // 获取操作信息
            String operationName = logMark.value() + resultMsg;
            // 获取相对URL
            String url = request.getRequestURI();
            // 获取请求参数
            String params = JSON.toJSONString(HttpContextUtil.getPara2Map(request));
            if (params.length() > 2000) {
                params = "请求参数过长，不进行记录";
            }
            // 进行日志记录
            LogInfo logInfo = new LogInfo();
            logInfo.setUserId(0L);
            logInfo.setUserName("");
            logInfo.setUserType(-1);
            logInfo.setOperationName(operationName);
            logInfo.setRequestIp(ip);
            logInfo.setSpendTime(spendTime);
            logInfo.setRequestUrl(url);
            logInfo.setRequestParams(params);

            kafkaProducer.sendMsg(topicName, logInfo);
        }
        return result;
    }

}