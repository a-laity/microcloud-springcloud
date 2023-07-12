package com.imooc.mapper;

import com.imooc.bean.Action;
import com.imooc.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface IActionDAO extends MyMapper<Action> {
    public Set<String> findAllByMember(String mid); // 获取权限信息
}
