package com.msy.plus.dto;

import com.msy.plus.entity.Permission;
import com.msy.plus.entity.RoleWithPermissionDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@Data
@Schema(name = "角色及权限传输实体")
@EqualsAndHashCode(callSuper = true)
public class RoleWithPermissionDTO extends RoleWithPermissionDO {
    private static final long serialVersionUID = -123223812341212L;
    /** 角色Id */
    @NotEmpty(message = "ID不能为空")
    @Schema(name = "角色Id", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    /** 角色名称 */
    @Schema(name = "角色名称")
    private String name;

    /** 角色编号 */
    @Schema(name = "角色编号")
    private String sn;

    /** 角色权限 */
    @Schema(name = "角色权限列表")
    private List<Permission> permissions;
}
