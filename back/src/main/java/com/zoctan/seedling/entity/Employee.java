package com.zoctan.seedling.entity;

import java.math.BigDecimal;
import javax.persistence.*;

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

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return salary
     */
    public BigDecimal getSalary() {
        return salary;
    }

    /**
     * @param salary
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     * @return deptId
     */
    public Long getDeptid() {
        return deptid;
    }

    /**
     * @param deptid
     */
    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return admin
     */
    public Boolean getAdmin() {
        return admin;
    }

    /**
     * @param admin
     */
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}