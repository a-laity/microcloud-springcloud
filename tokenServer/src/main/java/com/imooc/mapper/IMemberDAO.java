package com.imooc.mapper;

import com.imooc.bean.Member;
import com.imooc.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDAO extends MyMapper<Member> {

}
