package com.yly.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 角色权限中间表
 * @TableName role_permission
 */
@TableName(value ="role_permission")
@Data
public class RolePermissionDO implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;

    /**
     * 权限id
     */
    @TableField(value = "permission_id")
    private Integer permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}