package com.msy.plus.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;

    private Integer age;

    private Integer dept;

    @Column(name = "hireDate")
    private Date hiredate;

    /**
     * 状态 1正常 0离职
     */
    private Integer state;

    /**
     * 超级管理员身份 1超管 0普通
     */
    private Integer admin;
}