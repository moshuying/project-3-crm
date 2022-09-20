package com.msy.plus.dto;

import com.msy.plus.core.dto.AbstractConverter;
import com.msy.plus.entity.Baobei;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author jiuyuehe
 * @date 2022/08/30
 */
@Data
@Schema(name = "报备传输实体")
@EqualsAndHashCode(callSuper = true)
public class BaobeiDTO extends AbstractConverter<BaobeiDTO, Baobei> implements Serializable {
  private static final long serialVersionUID = 1473352811666797847L;

  @Schema(name = "报备单id", accessMode = Schema.AccessMode.READ_ONLY)
  private Integer id;


  @NotEmpty(message = "客户名称不能为空")
  private String customerName;

  @Schema(name = "账户名", accessMode = Schema.AccessMode.READ_ONLY, example = "admin")
  @NotEmpty(message = "账户名不能为空")
  @Size(min = 1, message = "账户名长度不能小于1")
  private String name;

  @Schema(name = "密码", example = "admin")
  @NotEmpty(message = "密码不能为空")
  @Size(min = 5, message = "密码长度不能小于5")
  private String password;

  @Override
  protected BaobeiDTO setDTO() {
    return this;
  }
}
