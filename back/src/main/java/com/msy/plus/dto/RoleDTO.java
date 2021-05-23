package com.msy.plus.dto;

import com.msy.plus.core.dto.AbstractConverter;
import com.msy.plus.entity.RoleDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * @author MoShuying
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

  /** 角色编号 */
  @Column(name = "sn")
  private String sn;

  @Override
  protected RoleDTO setDTO() {
    return this;
  }
}
