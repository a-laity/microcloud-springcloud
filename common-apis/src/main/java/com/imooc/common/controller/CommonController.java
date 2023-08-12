package com.imooc.common.controller;

import com.imooc.bean.DeptDTO;
import com.imooc.grace.result.GraceJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(value = "myApi", tags = "API测试接口")
@FeignClient("provider")
public interface CommonController {
    @ApiOperation(value = "部门查询", notes = "根据部门编号查询部门详细信息")
    @RequestMapping("/provider/getInfo")
    public GraceJSONResult getDeptInfo();


    @ApiOperation(value = "部门增加", notes = "增加新的部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptDTO", required = true,
                    dataType = "DeptDTO", value = "部门传输对象实例")
    })
    @PostMapping("add")
    public Object add(@RequestBody DeptDTO deptDTO);

    @PostMapping("add1")
    public Object add1(@RequestBody DeptDTO deptDTO);

    @PostMapping("add2") //TCC模式的调用
    public Object add2(@RequestBody DeptDTO deptDTO);

    @ApiOperation(value = "部门列表", notes = "查询部门的完整信息")
    @GetMapping("list")
    public Object list();
}
