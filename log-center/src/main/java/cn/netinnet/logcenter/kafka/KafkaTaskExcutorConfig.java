package cn.netinnet.logcenter.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * kafka任务线程池
 * @author ousp
 * @date 2020/6/12
 */
@Configuration
public class KafkaTaskExcutorConfig {
    @Value("${kafka.kafkaTaskExcutor.core_pool_size}")
    private int corePoolSize;
    @Value("${kafka.kafkaTaskExcutor.max_pool_size}")
    private int maxPoolSize;
    @Value("${kafka.kafkaTaskExcutor.queue_capacity}")
    private int queueCapacity;
    @Value("${kafka.kafkaTaskExcutor.keep_alive_seconds}")
    private int keepAliveSeconds;
    @Value("${kafka.kafkaTaskExcutor.thread_name_prefix}")
    private String threadNamePrefix;

    //配置处理kafka任务线程池
    @Bean("kafkaTaskExcutor")
    public Executor kafkaTaskExcutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(30);
        return executor;
    }
}
