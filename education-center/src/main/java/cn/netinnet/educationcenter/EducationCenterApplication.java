package cn.netinnet.educationcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages={"cn.netinnet"})
@MapperScan({"cn.netinnet.educationcenter.dao"})
@EnableTransactionManagement
@EnableEurekaClient
@EnableFeignClients(basePackages  = "cn.netinnet")
@EnableCircuitBreaker
public class EducationCenterApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(EducationCenterApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
