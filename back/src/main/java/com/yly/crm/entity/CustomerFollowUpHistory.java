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
 * @TableName customer_follow_up_history
 */
@TableName(value ="customer_follow_up_history")
@Data
public class CustomerFollowUpHistory implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 跟进时间
     */
    @TableField(value = "traceTime")
    private LocalDateTime tracetime;

    /**
     * 跟进内容 计划的详细内容
     */
    @TableField(value = "traceDetails")
    private String tracedetails;

    /**
     * 跟进方式 计划采用如电话、邀约上门等  数据字典
     */
    @TableField(value = "traceType")
    private Integer tracetype;

    /**
     * 跟进效果 优----3、中----2、差----1
     */
    @TableField(value = "traceResult")
    private Integer traceresult;

    /**
     * 跟进客户 编辑时不可编辑 潜在客户对象/客户对象
     */
    @TableField(value = "customerID")
    private Integer customerid;

    /**
     * 创建人 自动填入当前登录用户，用户不可更改 员工对象
     */
    @TableField(value = "inputUser")
    private Integer inputuser;

    /**
     * 跟进类型 0:潜在开发计划 1:客户跟进历史
     */
    @TableField(value = "type")
    private Integer type;

    /**
     *
     */
    @TableField(value = "comment")
    private String comment;


    /**
     * 业务部分类型
     */
    @TableField(value = "trace_biz_mode")
    private String traceBizMode;

    /**
     * 企业id
     */
    @TableField(value = "customer_ent_id")
    private String customerEntId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}