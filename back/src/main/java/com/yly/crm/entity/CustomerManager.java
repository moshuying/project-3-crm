package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 *
 * @TableName customer_manager
 */
@TableName(value ="customer_manager")
@Data
public class CustomerManager implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 客户年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 客户性别 页面为下拉框 1男 0女
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 电话号码
     */
    @TableField(value = "tel")
    private String tel;

    /**
     *
     */
    @TableField(value = "qq")
    private String qq;

    /**
     *
     */
    @TableField(value = "job")
    private Integer job;

    /**
     * 客户来源
     */
    @TableField(value = "source")
    private Integer source;

    /**
     * 负责人 填写为当前登录用户
     */
    @TableField(value = "seller")
    private Integer seller;

    /**
     *  创建人 填写为当前登录用户
     */
    @TableField(value = "inputUser")
    private Integer inputuser;

    /**
     *
     */
    @TableField(value = "inputTime")
    private LocalDateTime inputtime;

    /**
     * -2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 转正时间
     */
    @TableField(value = "positiveTime")
    private LocalDateTime positivetime;


    /**
     * 客户企业id
     */
    @TableField(value = "customer_ent_id")
    private String customerEntId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}