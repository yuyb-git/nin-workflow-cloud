package cn.netinnet.cloudcommon.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

/**
 *kafka配置
 * @author ousp
 * @date 2020/6/12
 */
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServer;

    /**
    * 创建一个kafka管理类，相当于rabbitMQ的管理类rabbitAdmin,没有此bean无法自定义的使用adminClient创建topic
    * @author ousp
    * @date 2020/6/12
    * @return org.springframework.kafka.core.KafkaAdmin
    */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> props = new HashMap<>();
        // 配置Kafka实例的连接地址
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServer);
        return new KafkaAdmin(props);
    }

    /**
    * kafka客户端，在spring中创建这个bean之后可以注入并且创建topic,用于集群环境，创建多个副本
    * @author ousp
    * @date 2020/6/12
    * @return org.apache.kafka.clients.admin.AdminClient
    */
    @Bean
    public AdminClient adminClient() {
        return AdminClient.create(kafkaAdmin().getConfig());
    }


}
