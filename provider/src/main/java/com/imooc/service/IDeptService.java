package com.imooc.service;


import com.imooc.bean.DeptDTO;

import java.util.List;

public interface IDeptService {
    /**
     * 根据部门的编号获取部门的完整信息
     *
     * @param id 要查询的部门编号
     * @return 编号存在则以DTO对象的形式返回部门数据，如果不存在返回null
     */
    public DeptDTO get(long id);

    /**
     * 增加部门对象
     *
     * @param dto 保存要增加部门的详细数据
     * @return 增加成功返回true，否则返回false
     */
    public boolean add(DeptDTO dto);

    public boolean add1(DeptDTO dto);

    /**
     * 列出所有的部门数据信息
     *
     * @return 全部数据的集合， 如果没有任何的部门数据则集合为空（size() == 0）
     */
    public List<DeptDTO> list();
    /**
     * 进行部门的分页数据加载操作
     * @param currentPage 当前所在页
     * @param lineSize 每页加载的数据行数
     * @param column 模糊查询的数据列
     * @param keyword 模糊查询关键字
     * @return 部门集合数据以及统计数据，返回的数据项包括：
     * 1、key = allDepts、value = List集合（部门的全部数据对象）
     * 2、key = allRecorders、value = 总记录数；
     * 3、key = allPages、value = 页数。
     */
    //public Map<String, Object> split(int currentPage, int lineSize, String column, String keyword);
}
