package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujunchen
 * @date 2023/5/15 21:25
 * @describe todo
 */

//http://localhost:8005/swagger-ui/index.html#/mycontroller swagger访问地址
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
//@RibbonClient(name="PROVIDERS",configuration = MyRule.class)
@EnableFeignClients
public class ConsumerSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerSpringApplication.class, args);
    }
}
