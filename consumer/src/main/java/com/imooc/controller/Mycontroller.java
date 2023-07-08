package com.imooc.controller;

import com.imooc.common.controller.CommonController;
import com.imooc.grace.result.GraceJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author xujunchen
 * @date 2023/5/15 21:47
 * @describe todo
 */
@RestController
public class Mycontroller {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CommonController commonController;

    @RequestMapping("/consumer/hello")
    public GraceJSONResult getHello() {
        System.out.println("Hello springboot!");
//        String RUL = "http://PROVIDERS/provider/getInfo";
//        GraceJSONResult forObject = restTemplate.getForObject(RUL, GraceJSONResult.class);
//        System.out.println(forObject.getData());
        GraceJSONResult deptInfo = commonController.getDeptInfo();
        return deptInfo;
    }
}
