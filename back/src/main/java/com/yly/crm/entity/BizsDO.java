package com.yly.crm.entity;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.yly.crm.const_enum.BizResources;
import com.yly.crm.const_enum.BizType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 商机管理表，管理商机报备，内外报备，提醒跟进等
 * @TableName bizs
 */
@TableName(value ="bizs",autoResultMap = true)
@Data
public class BizsDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商机、线索
     */
    @TableField(value = "biz_type")
    private BizType bizType;

    /**
     * 来源（渠道、官网、活动、其他）
     */
    @TableField(value = "biz_resources",typeHandler = FastjsonTypeHandler.class)
    private BizResources bizResources;

    /**
     * 最终报备客户名称
     */
    @TableField(value = "biz_ent_name")
    private String bizEntName;

    /**
     * 客户商机描述, 根据这个配备资源
     */
    @TableField(value = "biz_desc")
    private String bizDesc;

    /**
     * 商机阶段，消息提醒判断
     */
    @TableField(value = "biz_stage")
    private String bizStage;

    /**
     * 产品{id:name,id:name}
     */
    @TableField(value = "products",typeHandler = FastjsonTypeHandler.class)
    private List<Products> products;

    /**
     * 预计成交金额
     */
    @TableField(value = "biz_count")
    private Integer bizCount;

    /**
     * 预计成交时间
     */
    @TableField(value = "biz_okday")
    private Date bizOkday;

    /**
     * 对口业务经理
     */
    @TableField(value = "employee_id")
    private Integer employeeId;

    /**
     * 业务经理姓名（冗余，用于查询）
     */
    @TableField(value = "employee_name")
    private String employeeName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}