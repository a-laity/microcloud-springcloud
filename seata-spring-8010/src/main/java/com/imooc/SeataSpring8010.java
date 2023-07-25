package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xujunchen
 * @date 2023/7/18 21:38
 * @describe todo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SeataSpring8010 {
    public static void main(String[] args) {
        SpringApplication.run(SeataSpring8010.class, args);
    }
}
