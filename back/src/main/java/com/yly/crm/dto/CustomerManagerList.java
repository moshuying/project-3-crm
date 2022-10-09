package com.yly.crm.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CustomerManagerList {
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
    private String inputuser;

    private String inputUserId;

    private Date inputtime;

    /**
     * -2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     */
    private Integer status;

    /**
     * 转正时间
     */
    private Date positivetime;
}
