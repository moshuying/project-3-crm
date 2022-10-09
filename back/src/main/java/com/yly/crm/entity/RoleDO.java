package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 角色表
 * @TableName role
 */
@TableName(value ="role")
@Data
public class RoleDO implements Serializable {
    /**
     * 角色Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 角色编号
     */
    @TableField(value = "sn")
    private String sn;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}