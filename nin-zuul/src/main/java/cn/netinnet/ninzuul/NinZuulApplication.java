package cn.netinnet.ninzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages={"cn.netinnet"})
@EnableEurekaClient
@EnableCircuitBreaker
@EnableZuulProxy
public class NinZuulApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(NinZuulApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
