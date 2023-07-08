package com.imooc.my.mapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author xujunchen
 * @date 2023/5/15 22:27
 * @describe todo
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
