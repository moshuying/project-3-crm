package com.zoctan.seedling.dto;

import com.zoctan.seedling.core.dto.AbstractConverter;
import com.zoctan.seedling.entity.RoleDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Zoctan
 * @date 2018/07/15
 */
@Data
@Schema(name = "角色传输实体")
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends AbstractConverter<RoleDTO, RoleDO> implements Serializable {
  private static final long serialVersionUID = -145221735177809163L;

  @Schema(name = "角色Id", accessMode = Schema.AccessMode.READ_ONLY)
  private Long id;

  @Schema(name = "角色名称")
  private String name;

  @Override
  protected RoleDTO setDTO() {
    return this;
  }
}
