package com.imooc.mapper;

import com.imooc.bean.DeptDTO;
import com.imooc.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IDeptDao extends MyMapper<DeptDTO> {

}
