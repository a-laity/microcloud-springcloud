package com.imooc.controller;

import com.imooc.grace.result.GraceJSONResult;
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
    private static final String TOKEN = "eyJhdXRob3IiOiLmnY7lhbTljY4iLCJtb2R1bGUiOiJKV1QtVEVTVCIsImFsZyI6IkhTMjU2In0.eyJtc2ciOiLkuJbnlYzkuIrniIblj6_niLHnmoTogIHluIgg4oCU4oCUIOeIhuWPr-eIseeahOWwj-adjuiAgeW4iCIsInN1YiI6IntcInJpZHNcIjpcIlVTRVI7QURNSU47REVQVDtFTVA7Uk9MRVwiLFwibmFtZVwiOlwi5rKQ6KiA56eR5oqAIOKAlOKAlCDmnY7lhbTljY5cIixcIm1pZFwiOlwibXV5YW5cIn0iLCJzaXRlIjoid3d3Lnlvb3RrLmNvbSIsImlzcyI6Ik11eWFuWW9vdGsiLCJleHAiOjE2ODkwOTkwNzYsImlhdCI6MTY4ODk5OTA3NiwibmljZSI6Ikdvb2QgR29vZCBHb29kIiwianRpIjoieW9vdGstNGUyYzE3YWEtOTBhNS00NzEwLTgyZjUtNmU0YzgyYTNhYjM5In0.P0LkSjc_GtxWtC-nTAXB_0kqZMzhD_quAyyVlIZuXIM";

    @RequestMapping("/generalKey")
    public GraceJSONResult test() {
        return GraceJSONResult.ok(iTokenService.generalKey());
    }


    @RequestMapping("/TokenTest")
    public GraceJSONResult TokenTest() {
        Map<String, Object> map = new HashMap<>(); // 测试生成
        map.put("mid", "muyan");
        map.put("name", "沐言科技 —— 李兴华");
        map.put("rids", "USER;ADMIN;DEPT;EMP;ROLE"); // 用户角色信息
        String id = "yootk-" + UUID.randomUUID(); // 随意生成一个JWT-ID数据
        System.out.println(iTokenService.createToken(id, map));
        return GraceJSONResult.ok(iTokenService.createToken(id, map));
    }

    @RequestMapping("/parseToken")
    public GraceJSONResult parseToken() {
        return GraceJSONResult.ok(iTokenService.parseToken(TOKEN));
    }

    @RequestMapping("/verifyToken")
    public GraceJSONResult verifyToken() {
        return GraceJSONResult.ok(iTokenService.verifyToken(TOKEN));
    }

    @RequestMapping("/refreshToken")
    public GraceJSONResult refreshToken() {
        return GraceJSONResult.ok(iTokenService.refreshToken(TOKEN));
    }
}
