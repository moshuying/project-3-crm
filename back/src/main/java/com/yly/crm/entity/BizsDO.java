package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商机管理表，管理商机报备，内外报备，提醒跟进等
 * @TableName bizs
 */
@TableName(value ="bizs")
@Data
public class BizsDO implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商机、线索
     */
    private Object biz_type;

    /**
     * 来源（渠道、官网、活动、其他）
     */
    private Object biz_resources;

    /**
     * 最终报备客户名称
     */
    private String biz_ent_name;

    /**
     * 客户商机描述, 根据这个配备资源
     */
    private String biz_desc;

    /**
     * 商机阶段，消息提醒判断
     */
    private String biz_stage;

    /**
     * 产品{id:name,id:name}
     */
    private Object products;

    /**
     * 预计成交金额
     */
    private Integer biz_count;

    /**
     * 预计成交时间
     */
    private Date biz_okday;

    /**
     * 对口业务经理
     */
    private Integer employee_id;

    /**
     * 业务经理姓名（冗余，用于查询）
     */
    private String employee_name;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新时间
     */
    private Date update_time;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}