package com.imooc.bean;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class RoleDTO {

    private String rid;

    private String title;

    private String dbname;
}
