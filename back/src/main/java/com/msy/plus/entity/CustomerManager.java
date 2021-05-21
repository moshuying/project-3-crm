package com.msy.plus.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_manager")
public class CustomerManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 客户年龄
     */
    private Integer age;

    /**
     * 客户性别 页面为下拉框
     */
    private Integer gender;

    /**
     * 电话号码
     */
    private String tel;

    private String qq;

    private Integer job;

    /**
     * 客户来源
     */
    private Integer source;

    /**
     * 负责人 填写为当前登录用户
     */
    private Integer seller;

    /**
     *  创建人 填写为当前登录用户
     */
    @Column(name = "inputUser")
    private Integer inputuser;

    @Column(name = "inputTime")
    private Date inputtime;

    /**
     * -2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     */
    private Integer status;

    /**
     * 转正时间
     */
    @Column(name = "positiveTime")
    private Date positivetime;

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
     * 获取客户姓名
     *
     * @return name - 客户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置客户姓名
     *
     * @param name 客户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取客户年龄
     *
     * @return age - 客户年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置客户年龄
     *
     * @param age 客户年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取客户性别 页面为下拉框
     *
     * @return gender - 客户性别 页面为下拉框
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置客户性别 页面为下拉框
     *
     * @param gender 客户性别 页面为下拉框
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取电话号码
     *
     * @return tel - 电话号码
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话号码
     *
     * @param tel 电话号码
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return qq
     */
    public String getQq() {
        return qq;
    }

    /**
     * @param qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * @return job
     */
    public Integer getJob() {
        return job;
    }

    /**
     * @param job
     */
    public void setJob(Integer job) {
        this.job = job;
    }

    /**
     * 获取客户来源
     *
     * @return source - 客户来源
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置客户来源
     *
     * @param source 客户来源
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取负责人 填写为当前登录用户
     *
     * @return seller - 负责人 填写为当前登录用户
     */
    public Integer getSeller() {
        return seller;
    }

    /**
     * 设置负责人 填写为当前登录用户
     *
     * @param seller 负责人 填写为当前登录用户
     */
    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    /**
     * 获取 创建人 填写为当前登录用户
     *
     * @return inputUser -  创建人 填写为当前登录用户
     */
    public Integer getInputuser() {
        return inputuser;
    }

    /**
     * 设置 创建人 填写为当前登录用户
     *
     * @param inputuser  创建人 填写为当前登录用户
     */
    public void setInputuser(Integer inputuser) {
        this.inputuser = inputuser;
    }

    /**
     * @return inputTime
     */
    public Date getInputtime() {
        return inputtime;
    }

    /**
     * @param inputtime
     */
    public void setInputtime(Date inputtime) {
        this.inputtime = inputtime;
    }

    /**
     * 获取-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     *
     * @return status - -2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     *
     * @param status -2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取转正时间
     *
     * @return positiveTime - 转正时间
     */
    public Date getPositivetime() {
        return positivetime;
    }

    /**
     * 设置转正时间
     *
     * @param positivetime 转正时间
     */
    public void setPositivetime(Date positivetime) {
        this.positivetime = positivetime;
    }
}