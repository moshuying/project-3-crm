package com.msy.plus.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_follow_up_history")
public class CustomerFollowUpHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 跟进时间
     */
    @Column(name = "traceTime")
    private Date tracetime;

    /**
     * 跟进方式 计划采用如电话、邀约上门等  数据字典
     */
    @Column(name = "traceType")
    private Integer tracetype;

    /**
     * 跟进效果 优----3、中----2、差----1
     */
    @Column(name = "traceResult")
    private Integer traceresult;

    /**
     * 跟进客户 编辑时不可编辑 潜在客户对象/客户对象
     */
    @Column(name = "customerID")
    private Integer customerid;

    /**
     * 创建人 自动填入当前登录用户，用户不可更改 员工对象
     */
    @Column(name = "inputUser")
    private Integer inputuser;

    /**
     * 跟进类型 0:潜在开发计划 1:客户跟进历史
     */
    private Integer type;

    /**
     * 跟进内容 计划的详细内容
     */
    @Column(name = "traceDetails")
    private String tracedetails;

    private String comment;

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
     * 获取跟进时间
     *
     * @return traceTime - 跟进时间
     */
    public Date getTracetime() {
        return tracetime;
    }

    /**
     * 设置跟进时间
     *
     * @param tracetime 跟进时间
     */
    public void setTracetime(Date tracetime) {
        this.tracetime = tracetime;
    }

    /**
     * 获取跟进方式 计划采用如电话、邀约上门等  数据字典
     *
     * @return traceType - 跟进方式 计划采用如电话、邀约上门等  数据字典
     */
    public Integer getTracetype() {
        return tracetype;
    }

    /**
     * 设置跟进方式 计划采用如电话、邀约上门等  数据字典
     *
     * @param tracetype 跟进方式 计划采用如电话、邀约上门等  数据字典
     */
    public void setTracetype(Integer tracetype) {
        this.tracetype = tracetype;
    }

    /**
     * 获取跟进效果 优----3、中----2、差----1
     *
     * @return traceResult - 跟进效果 优----3、中----2、差----1
     */
    public Integer getTraceresult() {
        return traceresult;
    }

    /**
     * 设置跟进效果 优----3、中----2、差----1
     *
     * @param traceresult 跟进效果 优----3、中----2、差----1
     */
    public void setTraceresult(Integer traceresult) {
        this.traceresult = traceresult;
    }

    /**
     * 获取跟进客户 编辑时不可编辑 潜在客户对象/客户对象
     *
     * @return customerID - 跟进客户 编辑时不可编辑 潜在客户对象/客户对象
     */
    public Integer getCustomerid() {
        return customerid;
    }

    /**
     * 设置跟进客户 编辑时不可编辑 潜在客户对象/客户对象
     *
     * @param customerid 跟进客户 编辑时不可编辑 潜在客户对象/客户对象
     */
    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    /**
     * 获取创建人 自动填入当前登录用户，用户不可更改 员工对象
     *
     * @return inputUser - 创建人 自动填入当前登录用户，用户不可更改 员工对象
     */
    public Integer getInputuser() {
        return inputuser;
    }

    /**
     * 设置创建人 自动填入当前登录用户，用户不可更改 员工对象
     *
     * @param inputuser 创建人 自动填入当前登录用户，用户不可更改 员工对象
     */
    public void setInputuser(Integer inputuser) {
        this.inputuser = inputuser;
    }

    /**
     * 获取跟进类型 0:潜在开发计划 1:客户跟进历史
     *
     * @return type - 跟进类型 0:潜在开发计划 1:客户跟进历史
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置跟进类型 0:潜在开发计划 1:客户跟进历史
     *
     * @param type 跟进类型 0:潜在开发计划 1:客户跟进历史
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取跟进内容 计划的详细内容
     *
     * @return traceDetails - 跟进内容 计划的详细内容
     */
    public String getTracedetails() {
        return tracedetails;
    }

    /**
     * 设置跟进内容 计划的详细内容
     *
     * @param tracedetails 跟进内容 计划的详细内容
     */
    public void setTracedetails(String tracedetails) {
        this.tracedetails = tracedetails;
    }

    /**
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}