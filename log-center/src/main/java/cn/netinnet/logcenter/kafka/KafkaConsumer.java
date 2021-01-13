package cn.netinnet.logcenter.kafka;

import cn.netinnet.common.util.DateUtil;
import cn.netinnet.logcenter.domain.SysLog;
import cn.netinnet.logcenter.domain.SysLoginLog;
import cn.netinnet.logcenter.service.SysLogService;
import cn.netinnet.logcenter.service.SysLoginLogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @ClassName KafkaConsumer
 * @Description
 * @Author yuyb
 * @Date 2021/1/12 10:14
 */
@Component
public class KafkaConsumer {

    @Resource
    SysLogService sysLogService;
    @Resource
    SysLoginLogService sysLoginLogService;

    @KafkaListener(topics = {"${kafka.topic_name_consumer}"}, containerFactory = "batchFactory", groupId = "${kafka.consumer_group_id}")
    public void kafkaListener(List<String> logInfoList, Acknowledgment ack){
        try {
            for(String msg : logInfoList){
                JSONObject data = JSONObject.parseObject(msg);
                int msgType = data.getInteger("msgType");
                if(msgType == MsgTypeConstant.MSG_TYPE_LOGIN){
                    SysLoginLog sysLoginLog = data.toJavaObject(SysLoginLog.class);
                    sysLoginLog.setLogId(DateUtil.getUID());
                    sysLoginLog.setLoginTime(new Date());
                    sysLoginLogService.addLoginLogByAsync(sysLoginLog);
                }else if(msgType == MsgTypeConstant.MSG_TYPE_LOG){
                    SysLog sysLog = data.toJavaObject(SysLog.class);
                    sysLog.setLogId(DateUtil.getUID());
                    sysLog.setCreateTime(new Date());
                    sysLogService.addSysLogByAsync(sysLog);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();
        }
    }

}
