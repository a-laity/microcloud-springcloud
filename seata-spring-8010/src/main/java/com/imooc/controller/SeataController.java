package com.imooc.controller;

import com.imooc.bean.DeptDTO;
import com.imooc.common.controller.CommonController;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujunchen
 * @date 2023/7/18 22:50
 * @describe todo
 */
@RestController
@Slf4j
public class SeataController {
    @Autowired
    private CommonController commonController;

    @RequestMapping("/globalSeata")
    @GlobalTransactional // 全局事务控制
    public void globalSeataTest() {
        log.info("Seata全局事务id=================>{}", RootContext.getXID());
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptno((long) 6);
        deptDTO.setDname("vv");
        deptDTO.setLoc("长春");
        commonController.add(deptDTO);
        double i = 1 / 0;
    }


    @RequestMapping("/SeataTCC")
    @GlobalTransactional // 全局事务控制
    public void SeataTCC() {
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptno((long) 6);
        deptDTO.setDname("vv");
        deptDTO.setLoc("长春");
        commonController.add2(deptDTO);
        double i = 1 / 0;
    }


}
