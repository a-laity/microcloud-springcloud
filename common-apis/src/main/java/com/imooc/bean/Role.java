package com.imooc.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "role")
@Data
public class Role {
    @Id
    private String rid;
    @Column(name = "title")
    private String title;
    @Column(name = "dbname")
    private String dbname;
}
