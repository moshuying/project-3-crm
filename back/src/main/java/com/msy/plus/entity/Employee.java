package com.msy.plus.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import javax.persistence.*;

@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sn;

    private BigDecimal salary;

    @Column(name = "deptId")
    private Long deptid;

    private String password;

    private String email;

    private Integer age;

    private Boolean admin;
}