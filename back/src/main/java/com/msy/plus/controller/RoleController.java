package com.msy.plus.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.dto.RoleDTO;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色控制器
 *
 * @author MoShuying
 * @date 2018/05/27
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"角色接口"})
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
    final RoleDO role = this.roleService.getDetailById(id);
    return ResultGenerator.genOkResult(role);
  }

  @Operation(description = "角色列表")
  @GetMapping
  @ApiOperation(value="分页查询角色", notes="分页查询角色列表")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
          @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
  })
  public Result list(
      @RequestParam(defaultValue = "1") final Integer page,
      @RequestParam(defaultValue = "10") final Integer size) {
    PageHelper.startPage(page, size);
    final List<RoleDO> list = this.roleService.listAll();
    final PageInfo<RoleDO> pageInfo = new PageInfo<>(list);
    return ResultGenerator.genOkResult(pageInfo);
  }
}
