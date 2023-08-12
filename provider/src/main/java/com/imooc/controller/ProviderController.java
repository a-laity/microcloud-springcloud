package com.imooc.controller;

import com.imooc.bean.DeptDTO;
import com.imooc.common.controller.CommonController;
import com.imooc.grace.result.GraceJSONResult;
import com.imooc.grace.result.ResponseStatusEnum;
import com.imooc.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author xujunchen
 * @date 2023/5/15 22:16
 * @describe todo
 */
@RestController
@Slf4j
public class ProviderController implements CommonController {
    @Autowired
    private IDeptService deptService;

    public GraceJSONResult getDeptInfo() {
        DeptDTO deptDTO = deptService.get(1);

        return new GraceJSONResult(ResponseStatusEnum.SUCCESS, deptDTO);
    }

    public Object add(@RequestBody DeptDTO deptDTO) {    // 后面会修改参数模式为JSON
        this.printRequestHeaders("add");
        return this.deptService.add(deptDTO);
    }

    public Object list() {
        this.printRequestHeaders("list");
        return this.deptService.list();
    }

    public Object add1(@RequestBody DeptDTO deptDTO) {

        return this.deptService.add1(deptDTO);
    }
    public Object add2(@RequestBody DeptDTO deptDTO) {
        return this.deptService.add2(deptDTO);
    }

    private void printRequestHeaders(String restName) {    // 实现所有请求头信息的输出
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headerEnums = request.getHeaderNames();
        while (headerEnums.hasMoreElements()) {
            String headerName = headerEnums.nextElement();
            log.info("【{}】头信息：{} = {}", restName, headerName, request.getHeader(headerName));
        }
    }
}
