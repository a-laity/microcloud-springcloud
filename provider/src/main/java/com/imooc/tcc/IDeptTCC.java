package com.imooc.tcc;

import com.imooc.bean.DeptDTO;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

@LocalTCC
public interface IDeptTCC {
    @TwoPhaseBusinessAction(name = "deptTCCService", commitMethod = "commit", rollbackMethod = "rollback")
    public boolean prepareAdd(BusinessActionContext businessActionContext,
                              @BusinessActionContextParameter(paramName = "dept") DeptDTO deptDTO);

    public boolean commit(BusinessActionContext businessActionContext);

    public boolean rollback(BusinessActionContext businessActionContext);
}
