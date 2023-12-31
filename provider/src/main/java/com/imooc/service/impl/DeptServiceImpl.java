package com.imooc.service.impl;

import com.imooc.bean.DeptDTO;
import com.imooc.service.IDeptService;
import com.imooc.exception.CustomException;
import com.imooc.grace.result.ResponseStatusEnum;
import com.imooc.mapper.IDeptDao;
import com.imooc.tcc.IDeptTCC;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author xujunchen
 * @date 2023/5/15 23:05
 * @describe todo
 */
@Service
@Slf4j
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDao deptDao;
    @Autowired
    private IDeptTCC deptTCC;

    @Override
    @Transactional
    public DeptDTO get(long id) {
        Example example = new Example(DeptDTO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deptno", id);
        DeptDTO deptDTO = deptDao.selectOneByExample(example);
        if (Objects.isNull(deptDTO)) {
            throw new CustomException(ResponseStatusEnum.FAILED);
        }
        return deptDTO;
    }

    @Override
    @Transactional
    public boolean add(DeptDTO dto) {
        Example example = new Example(DeptDTO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deptno", dto.getDeptno());
        DeptDTO deptDTO = deptDao.selectOneByExample(example);
        if (deptDTO == null) {
            int insert = deptDao.insert(dto);
            if (insert > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean add1(DeptDTO dto) {
        log.info("Seata全局事务id=================>{}  add1", RootContext.getXID());
        int insert = deptDao.insert(dto);
        if (insert > 0) {
            return true;
        }
        return false;

    }

    public boolean add2(DeptDTO dto) {
        return deptTCC.prepareAdd(new BusinessActionContext(), dto);
    }

    @Override
    @Transactional
    public List<DeptDTO> list() {
        List<DeptDTO> deptDTOS = deptDao.selectAll();
        return deptDTOS;
    }
}
