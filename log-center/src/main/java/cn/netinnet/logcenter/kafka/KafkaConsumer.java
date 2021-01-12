package cn.netinnet.logcenter.kafka;

import cn.netinnet.common.util.DateUtil;
import cn.netinnet.logcenter.domain.SysLog;
import cn.netinnet.logcenter.service.SysLogService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
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

    @KafkaListener(topics = {"${kafka.topic_name_consumer}"}, containerFactory = "batchFactory", groupId = "${kafka.consumer_group_id}")
    public void kafkaListener(List<String> logInfoList){
        for(String msg : logInfoList){
            SysLog sysLog = JSONObject.parseObject(msg, SysLog.class);
            sysLog.setLogId(DateUtil.getUID());
            sysLog.setCreateTime(new Date());
            sysLogService.addSysLogByAsync(sysLog);
        }
    }

}
