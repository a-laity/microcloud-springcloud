package com.imooc.controller;

import com.imooc.bean.MemberDTO;
import com.imooc.grace.result.GraceJSONResult;
import com.imooc.service.IEncryptService;
import com.imooc.service.IMemberService;
import com.imooc.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MermberController {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IEncryptService encryptService; // yootk-starter-jwt模块提供的
    @Autowired
    private ITokenService tokenService; // yootk-starter-jwt模块提供的

    @PostMapping("/getMemberInfo")
    public GraceJSONResult getMemberInfo(@RequestBody MemberDTO memberDTO) {
        Map<String, Object> login = memberService.login(memberDTO);
        return GraceJSONResult.ok(login);
    }

    @RequestMapping("/create")
    public GraceJSONResult login(MemberDTO memberDTO) {
        // 对用户传入的密码信息进行加密处理
        memberDTO.setPassword(encryptService.getEncryptPassword(memberDTO.getPassword()));
        Map<String, Object> result = memberService.login(memberDTO); // 登录业务处理
        if (((Boolean) result.get("status"))) {  // 登录状态
            return GraceJSONResult.ok(tokenService.createToken(result.get("mid").toString(), (Map<String, Object>) result.get("resource")));
        }
        return GraceJSONResult.errorMsg("账号密码错误！");
    }
}
