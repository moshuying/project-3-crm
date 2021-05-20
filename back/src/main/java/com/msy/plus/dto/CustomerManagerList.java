package com.msy.plus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Setter
public class CustomerManagerList {
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

    private String job;

    /**
     * 客户来源
     */
    private String source;

    /**
     * 负责人 填写为当前登录用户
     */
    private Integer seller;

    /**
     *  创建人 填写为当前登录用户
     */
    @Column(name = "inputUser")
    private String inputuser;

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
}
