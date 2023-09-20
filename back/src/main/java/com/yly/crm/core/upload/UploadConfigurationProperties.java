package com.yly.crm.core.upload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

/**
 * 上传配置
 *
 * @author MoShuying
 * @date 2019/08/13
 */
@Data
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadConfigurationProperties {
  /** 本地路径 */
  private String localPath;

  /** 最小 */
  private DataSize min;

  /** 最大 */
  private DataSize max;
}
