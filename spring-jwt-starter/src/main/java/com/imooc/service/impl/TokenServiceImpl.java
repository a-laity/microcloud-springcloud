package com.imooc.service.impl;

import com.alibaba.fastjson2.JSON;
import com.imooc.config.JWTConfigProperties;
import com.imooc.service.ITokenService;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@Service
public class TokenServiceImpl implements ITokenService {
    @Autowired
    private JWTConfigProperties jwtConfigProperties; // 获取JWT的相关配置属性
    @Value("${spring.application.name}") // 通过SpEL进行配置注入
    private String applicationName; // 应用名称
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 签名算法

    @Override
    public SecretKey generalKey() {
        byte[] bytes = Base64.decodeBase64(Base64.encodeBase64(jwtConfigProperties.getSecret().getBytes()));//获取加密后的密钥
        SecretKeySpec key = new SecretKeySpec(bytes, 0, bytes.length, "HmacSHA256");//拿到加密后的key
        System.out.println(key);
        return key;
    }

    @Override
    public String createToken(String id, Map<String, Object> subject) {
        // 使用JWT数据结构进行开发，目的之一就是不需要进行JWT数据的分布式存储，所以所谓的缓存组件、数据库都用不到
        // 所有的Token都存在有保存时效的问题，所以就需要通过当前时间来进行计算
        Date nowDate = new Date(); // 获取当前的日期时间
        Date expireDate = new Date(nowDate.getTime() + this.jwtConfigProperties.getExpire() * 1000); // 证书过期时间
        Map<String, Object> cliams = new HashMap<>(); // 保存所有附加数据
        cliams.put("site", "www.yootk.com"); // 视频下载地址，顶部有一个下载资源
        cliams.put("msg", "世界上爆可爱的老师 —— 爆可爱的小李老师"); // 随便添加内容
        cliams.put("nice", "Good Good Good");
        Map<String, Object> headers = new HashMap<>(); // 保存头信息
        headers.put("author", "李兴华"); // 作者，也可以通过配置处理
        // 后续由于很多的模块都会引用此组件，所以为了后续的安全，最佳的做法就是设置一个模块名称的信息
        headers.put("module", this.applicationName);
        JwtBuilder builder = null;
        builder = Jwts.builder()    // 进行JWTBuilder对象实例化
                .setClaims(cliams) // 保存附加的数据内容
                .setHeader(headers) // 保存头信息
                .setId(id)// 保存ID信息
                .setIssuedAt(nowDate) // 签发时间
                .setIssuer(jwtConfigProperties.getIssuer()) // 设置签发者
                .setSubject(JSON.toJSONString(subject)) // 所要传递的数据转为JSON
                .signWith(signatureAlgorithm, this.generalKey()) // 获取签名算法
                .setExpiration(expireDate); // 配置失效时间
        return builder.compact(); // 创建Token
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        if (this.verifyToken(token)) {  // 只有正确的时候再进行Token解析
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token);
            return claims;
        }
        return null; // 解析失败返回null
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token).getBody();
            return true; // 没有异常就返回true
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public String refreshToken(String token) {
        if (this.verifyToken(token)) {
            Jws<Claims> jws = this.parseToken(token); // 解析Token数据
            return this.createToken(jws.getBody().getId(), JSON.parseObject(jws.getBody().getSubject(), Map.class));
        }
        return null;
    }
}
