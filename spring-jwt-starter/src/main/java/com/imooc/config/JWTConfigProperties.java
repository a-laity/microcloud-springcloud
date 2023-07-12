package com.imooc.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class JWTConfigProperties {
    @Value("${yootk.security.config.jwt.sign}")
    private String sign; // 保存签名信息
    @Value("${yootk.security.config.jwt.issuer}")
    private String issuer; // 证书签发者
    @Value("${yootk.security.config.jwt.secret}")
    private String secret; // 加密的密钥
    @Value("${yootk.security.config.jwt.expire}")
    private long expire; // 失效时间
}
