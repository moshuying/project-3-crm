package com.zoctan.seedling.controller;

import com.zoctan.seedling.core.response.Result;
import com.zoctan.seedling.core.response.ResultGenerator;
import com.zoctan.seedling.core.upload.UploadConfigurationProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 上传控制器
 *
 * @author Zoctan
 * @date 2019/08/13
 */
@Tag(name = "上传接口")
@RestController
@RequestMapping("/upload")
public class UploadController {
  @Resource private UploadConfigurationProperties uploadProperties;

  @Operation(description = "上传文件")
  @Parameter(name = "file", description = "文件", required = true, in = ParameterIn.QUERY)
  @PostMapping
  public Result uploadImage(@RequestParam("file") final MultipartFile file) {
    if (file.isEmpty()) {
      return ResultGenerator.genFailedResult("请选择文件");
    }
    final DataSize fileSize = DataSize.ofBytes(file.getSize());
    final DataSize maxData = this.uploadProperties.getMax();
    final DataSize minData = this.uploadProperties.getMin();
    final String localPath = this.uploadProperties.getLocalPath();
    if (maxData.toBytes() < fileSize.toBytes() || fileSize.toBytes() < minData.toBytes()) {
      return ResultGenerator.genFailedResult("请上传%ldK~%ldM大小的文件", minData, maxData);
    }
    try {
      // 文件写入指定路径
      final Path filePath = Paths.get(localPath + file.getOriginalFilename());
      // 如果没有files文件夹，则创建
      if (!Files.isWritable(filePath)) {
        Files.createDirectories(Paths.get(localPath));
      }
      Files.write(filePath, file.getBytes());
      return ResultGenerator.genOkResult(file.getOriginalFilename());
    } catch (final IOException e) {
      e.printStackTrace();
      return ResultGenerator.genFailedResult();
    }
  }

  /**
   * 根据图片名称返回本地图片
   *
   * @param name 图片名称
   * @return 返回本地图片
   */
  @Operation(description = "获取文件")
  @Parameter(name = "name", description = "文件名", required = true, in = ParameterIn.PATH)
  @GetMapping("/{name:.+}")
  public Result getImage(
      @PathVariable final String name, final HttpServletResponse httpServletResponse) {
    try {
      final String localPath = this.uploadProperties.getLocalPath();
      final File file = new File(localPath + name);
      final FileInputStream inputStream = new FileInputStream(file);
      final byte[] bytes = new byte[(int) file.length()];
      inputStream.read(bytes);
      final OutputStream os = httpServletResponse.getOutputStream();
      os.write(bytes);
      os.flush();
      os.close();
      return ResultGenerator.genOkResult();
    } catch (final IOException e) {
      e.printStackTrace();
      return ResultGenerator.genFailedResult("读取文件出错：%s", e.getMessage());
    }
  }

  @Operation(description = "删除文件")
  @Parameter(name = "name", description = "文件名", required = true)
  @DeleteMapping("/{name:.+}")
  public Result removeImage(@PathVariable final String name) {
    final String localPath = this.uploadProperties.getLocalPath();
    final File file = new File(localPath + name);
    return ResultGenerator.genOkResult(file.delete());
  }
}
