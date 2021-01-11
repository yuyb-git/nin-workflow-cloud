package cn.netinnet.educationcenter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author chen.wb
 * @version V1.0
 * @Description: 开启spring session支持，实现session共享
 * @Date 2017-11-30
 **/
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisSessionConfig {
}
