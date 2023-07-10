package com.imooc.controller;

import com.imooc.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class TestController {
    @Autowired
    private ITokenService iTokenService;
    @RequestMapping("/generalKey")
    public void test(){
        iTokenService.generalKey();
    }


    @RequestMapping("/TokenTest")
    public void TokenTest(){
        Map<String, Object> map = new HashMap<>(); // 测试生成
        map.put("mid", "muyan");
        map.put("name", "沐言科技 —— 李兴华");
        map.put("rids", "USER;ADMIN;DEPT;EMP;ROLE"); // 用户角色信息
        String id = "yootk-" + UUID.randomUUID(); // 随意生成一个JWT-ID数据
        iTokenService.createToken(id,map);
    }

}
