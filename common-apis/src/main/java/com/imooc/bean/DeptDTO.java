package com.imooc.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author xujunchen
 * @date 2023/5/15 21:00
 * @describe todo
 */
@Data // Lombok注解，自动生成所需要的类结构
@Table(name = "dept")
public class DeptDTO implements Serializable { // 定义数据传输类
    @Column(name = "deptno")
    private Long deptno; // 部门编号
    @Column(name = "dname")
    private String dname; // 部门名称
    @Column(name = "loc")
    private String loc; // 部门位置
}
