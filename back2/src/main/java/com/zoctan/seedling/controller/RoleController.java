package com.zoctan.seedling.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.seedling.core.response.Result;
import com.zoctan.seedling.core.response.ResultGenerator;
import com.zoctan.seedling.dto.RoleDTO;
import com.zoctan.seedling.entity.RoleDO;
import com.zoctan.seedling.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色控制器
 *
 * @author Zoctan
 * @date 2018/05/27
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {
  @Resource private RoleService roleService;

  @Operation(description = "角色添加")
  @PostMapping
  public Result add(@RequestBody final RoleDTO roleDTO) {
    this.roleService.save(roleDTO);
    return ResultGenerator.genOkResult();
  }

  @Operation(description = "角色删除")
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable final Long id) {
    this.roleService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

  @Operation(description = "角色更新")
  @PutMapping
  public Result update(@RequestBody final RoleDTO roleDTO) {
    this.roleService.update(roleDTO);
    return ResultGenerator.genOkResult();
  }

  @Operation(description = "角色详情")
  @GetMapping("/{id}")
  public Result detail(@PathVariable final Long id) {
    final RoleDO role = this.roleService.getById(id);
    return ResultGenerator.genOkResult(role);
  }

  @Operation(description = "角色列表")
  @GetMapping
  public Result list(
      @RequestParam(defaultValue = "0") final Integer page,
      @RequestParam(defaultValue = "0") final Integer size) {
    PageHelper.startPage(page, size);
    final List<RoleDO> list = this.roleService.listAll();
    final PageInfo<RoleDO> pageInfo = new PageInfo<>(list);
    return ResultGenerator.genOkResult(pageInfo);
  }
}
