package com.imooc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "yootk.security.config.password.encrypt")
public class EncryptConfigProperties {
    private Integer repeat; // 定义重复的次数
    private String salt; // 加密的盐值
}
