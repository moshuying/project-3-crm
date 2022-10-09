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
 * @TableName customer_handover
 */
@TableName(value ="customer_handover")
@Data
public class CustomerHandover implements Serializable {
    /**
     *
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户 客户对象
     */
    @TableField(value = "customerID")
    private Integer customerid;

    /**
     * 移交人员 实行移交操作的管理人员
     */
    @TableField(value = "transUser")
    private Integer transuser;

    /**
     *
     */
    @TableField(value = "transTime")
    private LocalDateTime transtime;

    /**
     * 老市场专员 客户上的原始市场人员
     */
    @TableField(value = "oldSeller")
    private Integer oldseller;

    /**
     * 新市场专员 由公司重新指派后的新市场人员
     */
    @TableField(value = "newSeller")
    private Integer newseller;

    /**
     * 移交原因
     */
    @TableField(value = "transReason")
    private String transreason;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}