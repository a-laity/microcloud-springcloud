package com.imooc.service.impl;

import com.imooc.bean.DeptDTO;
import com.imooc.service.IDeptService;
import com.imooc.exception.CustomException;
import com.imooc.grace.result.ResponseStatusEnum;
import com.imooc.mapper.IDeptDao;
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
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDao deptDao;

    @Override
    @Transactional
    public DeptDTO get(long id) {
        Example example = new Example(DeptDTO.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("deptno", id);
        DeptDTO deptDTO = deptDao.selectOneByExample(example);
        if(Objects.isNull(deptDTO)){
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

    @Override
    @Transactional
    public List<DeptDTO> list() {
        List<DeptDTO> deptDTOS = deptDao.selectAll();
        return deptDTOS;
    }
}
