package com.zoctan.seedling.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 资源地址
     */
    private String expression;
}