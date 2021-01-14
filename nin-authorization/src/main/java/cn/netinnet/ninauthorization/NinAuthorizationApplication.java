package cn.netinnet.ninauthorization;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages={"cn.netinnet"})
@EnableEurekaClient
@EnableCircuitBreaker
@EnableFeignClients(basePackages = "cn.netinnet")
@MapperScan({"cn.netinnet.ninauthorization.dao"})
public class NinAuthorizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NinAuthorizationApplication.class, args);
    }

}
