package cn.netinnet.usercenter;

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
@EnableFeignClients
@MapperScan({"cn.netinnet.usercenter.dao"})
public class UserCenterApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(UserCenterApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
