package com.imooc.service.impl;

import com.imooc.config.EncryptConfigProperties;
import com.imooc.service.IEncryptService;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptServiceImpl implements IEncryptService {
    @Autowired
    private EncryptConfigProperties encryptConfigProperties;
    private static MessageDigest MD5_DIGEST; // MD5加密处理
    private static final Base64.Encoder BASE64_ENCODER = Base64.getEncoder(); // 加密器

    static {    // 初始化操作
        try {
            MD5_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getEncryptPassword(String password) {
        String SlatPassword = encryptConfigProperties.getSalt() + password;
        for (int i = 0; i < encryptConfigProperties.getRepeat(); i++) {
            SlatPassword = BASE64_ENCODER.encodeToString(MD5_DIGEST.digest(SlatPassword.getBytes()));

        }
        return SlatPassword;
    }
}
