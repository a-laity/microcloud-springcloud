package com.imooc.mapper;

import com.imooc.bean.Role;
import com.imooc.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface IRoleDAO extends MyMapper<Role> {
    public Set<String> findAllByMember(String mid); // 根据用户名查询角色
}
