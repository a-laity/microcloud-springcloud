package com.imooc.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "action")
public class Action {
    @Id
    private String actid;
    @Column
    private String title;
    @Column
    private String rid;
    @Column
    private String dbname;
}
