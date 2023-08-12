package com.imooc.tcc.impl;

import com.alibaba.fastjson.JSONObject;
import com.imooc.bean.DeptDTO;
import com.imooc.mapper.IDeptDao;
import com.imooc.tcc.IDeptTCC;
import com.imooc.tcc.TccResultStore;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class DeptTCCImpl implements IDeptTCC {
    @Resource
    private IDeptDao deptDao; // 直接实现数据层的处理

    @Override
    public boolean prepareAdd(BusinessActionContext businessActionContext, DeptDTO deptDTO) {
        log.info("【第一阶段】xid = {}、dept = {}", businessActionContext.getXid(), deptDTO);
        // 此时的数据要进行第一阶段的验证，下面是对该数据的简单验证，尝试一下能否使用
        if (deptDTO.getDname() == null || "".equals(deptDTO.getDname())) {
            throw new RuntimeException("部门信息错误"); // 手工抛出异常
        }
        TccResultStore.setResultStore(getClass(), businessActionContext.getXid(), "d");
        return true;

    }

    @Override
    public boolean commit(BusinessActionContext businessActionContext) {
        // 将接收到的数据以JSONObject的形式返回，而后通过FastJSON的操作将其转为对象实例
        DeptDTO dept = ((JSONObject) businessActionContext.getActionContext("dept")).toJavaObject(DeptDTO.class);
        log.info("【第二阶段】事务提交，xid = {}、dept = {}", businessActionContext.getXid(), dept);
        // 需要根据第一阶段的prepareAdd()方法来决定是否提交
        if (TccResultStore.getResultStore(getClass(),
                businessActionContext.getXid()) == null) { // 防止重复提交
            return true;
        }
        try {
            return (deptDao.insert(dept) > 0);
        } finally {
            TccResultStore.revomeResultStore(getClass(), businessActionContext.getXid());
        }
    }

    @Override
    public boolean rollback(BusinessActionContext businessActionContext) {
        DeptDTO dept = ((JSONObject) businessActionContext.getActionContext("dept")).toJavaObject(DeptDTO.class);
        log.info("【第二阶段】事务回滚，xid = {}、dept = {}", businessActionContext.getXid(), dept);
        if (TccResultStore.getResultStore(getClass(),
                businessActionContext.getXid()) == null) { // 防止重复提交
            return true;
        }
        TccResultStore.revomeResultStore(getClass(), businessActionContext.getXid());
        return true;
    }
}
