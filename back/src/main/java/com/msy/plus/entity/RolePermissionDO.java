package com.msy.plus.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 角色和权限中间表数据模型
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RolePermissionDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色ID
     */
    private Long role_id;

    /**
     * 权限ID
     */
    private Long permission_id;
}
