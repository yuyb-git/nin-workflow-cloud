package cn.netinnet.ninzuul;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages={"cn.netinnet"})
@EnableEurekaClient
@EnableZuulProxy
@MapperScan({"cn.netinnet.ninzuul.dao"})
public class NinZuulApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(NinZuulApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
