package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 先录入商机，再开始报备
 * @TableName baobei
 */
@TableName(value ="baobei")
@Data
public class BaobeiDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Integer id;

    /**
     * 
     */
    @TableField(value = "biz_id")
    private Integer bizId;

    /**
     * 渠道企业id
     */
    @TableField(value = "cent_id")
    private Integer centId;

    /**
     * 渠道企业名称
     */
    @TableField(value = "cent_name")
    private String centName;

    /**
     * 保护到期时间，每次延长3个月
     */
    @TableField(value = "protected_period")
    private Date protectedPeriod;

    /**
     * 渠道销售id
     */
    @TableField(value = "cuser_id")
    private Integer cuserId;

    /**
     * 描述信息
     */
    @TableField(value = "project_desc")
    private String projectDesc;

    /**
     * 
     */
    @TableField(value = "create_at")
    private Date createAt;

    /**
     * 报备人
     */
    @TableField(value = "create_user_id")
    private Integer createUserId;

    /**
     * 报备状态
     */
    @TableField(value = "success")
    private Object success;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}