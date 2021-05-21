package com.msy.plus.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "customer_handover")
public class CustomerHandover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户 客户对象
     */
    @Column(name = "customerID")
    private Integer customerid;

    /**
     * 移交人员 实行移交操作的管理人员
     */
    @Column(name = "transUser")
    private Integer transuser;

    @Column(name = "transTime")
    private Date transtime;

    /**
     * 老市场专员 客户上的原始市场人员
     */
    @Column(name = "oldSeller")
    private Integer oldseller;

    /**
     * 新市场专员 由公司重新指派后的新市场人员
     */
    @Column(name = "newSeller")
    private Integer newseller;

    /**
     * 移交原因
     */
    @Column(name = "transReason")
    private String transreason;

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
     * 获取客户 客户对象
     *
     * @return customerID - 客户 客户对象
     */
    public Integer getCustomerid() {
        return customerid;
    }

    /**
     * 设置客户 客户对象
     *
     * @param customerid 客户 客户对象
     */
    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    /**
     * 获取移交人员 实行移交操作的管理人员
     *
     * @return transUser - 移交人员 实行移交操作的管理人员
     */
    public Integer getTransuser() {
        return transuser;
    }

    /**
     * 设置移交人员 实行移交操作的管理人员
     *
     * @param transuser 移交人员 实行移交操作的管理人员
     */
    public void setTransuser(Integer transuser) {
        this.transuser = transuser;
    }

    /**
     * @return transTime
     */
    public Date getTranstime() {
        return transtime;
    }

    /**
     * @param transtime
     */
    public void setTranstime(Date transtime) {
        this.transtime = transtime;
    }

    /**
     * 获取老市场专员 客户上的原始市场人员
     *
     * @return oldSeller - 老市场专员 客户上的原始市场人员
     */
    public Integer getOldseller() {
        return oldseller;
    }

    /**
     * 设置老市场专员 客户上的原始市场人员
     *
     * @param oldseller 老市场专员 客户上的原始市场人员
     */
    public void setOldseller(Integer oldseller) {
        this.oldseller = oldseller;
    }

    /**
     * 获取新市场专员 由公司重新指派后的新市场人员
     *
     * @return newSeller - 新市场专员 由公司重新指派后的新市场人员
     */
    public Integer getNewseller() {
        return newseller;
    }

    /**
     * 设置新市场专员 由公司重新指派后的新市场人员
     *
     * @param newseller 新市场专员 由公司重新指派后的新市场人员
     */
    public void setNewseller(Integer newseller) {
        this.newseller = newseller;
    }

    /**
     * 获取移交原因
     *
     * @return transReason - 移交原因
     */
    public String getTransreason() {
        return transreason;
    }

    /**
     * 设置移交原因
     *
     * @param transreason 移交原因
     */
    public void setTransreason(String transreason) {
        this.transreason = transreason;
    }
}