package com.msy.plus.entity;

import java.util.Date;
import javax.persistence.*;

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    /**
     * 多对多的关系 返回信息为角色对象
     */
    private Integer role;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
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
     * @return dept
     */
    public Integer getDept() {
        return dept;
    }

    /**
     * @param dept
     */
    public void setDept(Integer dept) {
        this.dept = dept;
    }

    /**
     * @return hireDate
     */
    public Date getHiredate() {
        return hiredate;
    }

    /**
     * @param hiredate
     */
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * 获取状态 1正常 0离职
     *
     * @return state - 状态 1正常 0离职
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态 1正常 0离职
     *
     * @param state 状态 1正常 0离职
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取超级管理员身份 1超管 0普通
     *
     * @return admin - 超级管理员身份 1超管 0普通
     */
    public Integer getAdmin() {
        return admin;
    }

    /**
     * 设置超级管理员身份 1超管 0普通
     *
     * @param admin 超级管理员身份 1超管 0普通
     */
    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    /**
     * 获取多对多的关系 返回信息为角色对象
     *
     * @return role - 多对多的关系 返回信息为角色对象
     */
    public Integer getRole() {
        return role;
    }

    /**
     * 设置多对多的关系 返回信息为角色对象
     *
     * @param role 多对多的关系 返回信息为角色对象
     */
    public void setRole(Integer role) {
        this.role = role;
    }
}