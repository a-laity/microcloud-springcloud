package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author xujunchen
 * @date 2023/5/15 22:53
 * @describe todo
 */
@SpringBootApplication
@MapperScan("com.imooc.mapper")
@EnableDiscoveryClient
public class ProviderSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderSpringApplication.class, args);
    }
}
