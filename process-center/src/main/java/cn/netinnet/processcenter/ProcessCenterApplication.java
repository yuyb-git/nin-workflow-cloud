package cn.netinnet.processcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Administrator
 */
@SpringBootApplication(scanBasePackages={"cn.netinnet"})
@EnableEurekaClient
@EnableFeignClients(basePackages  = "cn.netinnet")
@MapperScan({"cn.netinnet.processcenter.dao"})
public class ProcessCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProcessCenterApplication.class, args);
    }

}
