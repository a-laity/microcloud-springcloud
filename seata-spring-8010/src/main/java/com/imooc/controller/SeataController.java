package com.imooc.controller;

import com.imooc.bean.DeptDTO;
import com.imooc.common.controller.CommonController;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujunchen
 * @date 2023/7/18 22:50
 * @describe todo
 */
@RestController
public class SeataController {
    @Autowired
    private CommonController commonController;

    @RequestMapping("/globalSeata")
    @GlobalTransactional // 全局事务控制
    public void globalSeataTest() {
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptno((long) 1);
        deptDTO.setDname("vv");
        deptDTO.setLoc("长春");
        commonController.add1(deptDTO);
    }
}
