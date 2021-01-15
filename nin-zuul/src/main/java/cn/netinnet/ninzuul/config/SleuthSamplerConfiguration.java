package cn.netinnet.ninzuul.config;

import brave.sampler.Sampler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.sleuth.sampler.ProbabilityBasedSampler;
import org.springframework.cloud.sleuth.sampler.SamplerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SleuthSamplerConfiguration
 * @Description 避免启动加载sleuth时主线程与redis配置死锁
 * @Author yuyb
 * @Date 2021/1/8 14:21
 */
@Configuration
public class SleuthSamplerConfiguration {

    @Value("${spring.sleuth.sampler.probability}")
    private String probability;

    @Bean
    public Sampler defaultSampler() throws Exception {
        Float f = new Float(probability);
        SamplerProperties samplerProperties = new SamplerProperties();
        samplerProperties.setProbability(f);
        ProbabilityBasedSampler sampler = new ProbabilityBasedSampler(samplerProperties);
        return sampler;
    }

}
