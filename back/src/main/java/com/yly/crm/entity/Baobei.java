package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName baobei
 */
@TableName(value ="baobei")
@Data
public class Baobei implements Serializable {
    /**
     * 
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 
     */
    @TableField(value = "product_id")
    private Integer productId;

    /**
     * 
     */
    @TableField(value = "product_name")
    private String productName;

    /**
     * 
     */
    @TableField(value = "customer_name")
    private String customerName;

    /**
     * 描述信息
     */
    @TableField(value = "project_desc")
    private String projectDesc;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 
     */
    @TableField(value = "create_at")
    private LocalDateTime createAt;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}