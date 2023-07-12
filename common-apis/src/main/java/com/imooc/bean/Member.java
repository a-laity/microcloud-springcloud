package com.imooc.bean;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "member")
public class Member {
    @Id
    private String mid;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "locked")
    private Integer locked;
    @Column(name = "dbname")
    private String dbname;
}
