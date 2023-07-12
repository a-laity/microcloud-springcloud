package com.imooc;

import com.imooc.config.EncryptConfigProperties;
import com.imooc.config.JWTConfigProperties;
import com.imooc.service.IEncryptService;
import com.imooc.service.ITokenService;
import com.imooc.service.impl.EncryptServiceImpl;
import com.imooc.service.impl.TokenServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(JWTConfigProperties.class)
@EnableConfigurationProperties({EncryptConfigProperties.class})
public class JWTAutoConfiguration {

    @Bean
    public ITokenService getTokenService() {
        return new TokenServiceImpl();
    }

    @Bean
    public IEncryptService getEncryptService() {
        return new EncryptServiceImpl();
    }
}
