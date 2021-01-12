package cn.netinnet.cloudcommon.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @ClassName KafkaProducer
 * @Description
 * @Author yuyb
 * @Date 2021/1/12 10:40
 */
@Component
public class KafkaProducer {

    @Lazy
    @Resource
    private AdminClient adminClient;
    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * 创建topic主题（一般用不到）
     * @param topicName      topic主题
     * @param partitions     分区数
     * @param replication    副本数
     * @author ousp
     * @date 2020/6/12
     */
    public synchronized void createTopic(String topicName, int partitions, short replication) {
        if (!isTopicExist(topicName)) {
            NewTopic topic = new NewTopic(topicName, partitions, replication);
            adminClient.createTopics(Collections.singletonList(topic));
        }
    }

    /**
     * 检查主题是否存在
     * @param topicName  topic主题
     * @author ousp
     * @date 2020/6/12
     * @return boolean
     */
    private boolean isTopicExist(String topicName) {
        return adminClient.describeTopics(Collections.singletonList(topicName)) != null;
    }

    /**
     *  发送kafka消息
     * @param topicName  主题
     * @param sendData   数据
     * @author ousp
     * @date 2020/6/12
     */
    public void sendMsg(String topicName, Object sendData) {
        if (sendData == null) {
            return;
        }
        if (!isTopicExist(topicName)) {
            createTopic(topicName, 1, (short) 1);
        }
        kafkaTemplate.send(topicName, JSONObject.toJSONString(sendData));
    }

}
