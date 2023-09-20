package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.dto.RoleWithPermissionDTO;
import com.yly.crm.entity.RoleDO;
import com.yly.crm.entity.RolePermissionDO;
import com.yly.crm.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
  public Result add(@RequestBody final RoleWithPermissionDTO roleDTO) {
    if(roleDTO.getPermissions()==null){
      return ResultGenerator.genFailedResult("尚未添加角色权限");
    }
    try{
      this.roleService.save(roleDTO);
    }catch (DuplicateKeyException e){
      return ResultGenerator.genFailedResult("提交的信息中包含已存在的字段");
    }
    List<Long> temp = new ArrayList<>();
    roleDTO.getPermissions().forEach(e->{ temp.add(e.getId()); });
    this.roleService.savePermissions(roleDTO.getId(),temp);
    return ResultGenerator.genOkResult();
  }

  @Operation(description = "角色删除")
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable final Long id) {
    List<RolePermissionDO> raw = this.roleService.getAllRolePermissionTableRow(id);
    for(RolePermissionDO e :raw){
      this.roleService.deleteRolePermissionItem(id,e.getId());
    }
    this.roleService.removeById(id);
    return ResultGenerator.genOkResult();
  }

  @Operation(description = "角色更新")
  @PutMapping
  public Result update(@RequestBody final RoleWithPermissionDTO roleWithPermissionDTO) {
    // 更新用户基本信息
    this.roleService.save(roleWithPermissionDTO);
    List<Long> nowPermissions = new ArrayList<>();
    if(roleWithPermissionDTO.getPermissions()==null){
      return ResultGenerator.genOkResult();
    }

    List<RolePermissionDO> rawPer = this.roleService.getAllRolePermissionTableRow(roleWithPermissionDTO.getId());
    // 表中权限信息去重
    Set<Long> raw = new HashSet<>();
    for(RolePermissionDO e: rawPer){
      raw.add(e.getId());
    }

    roleWithPermissionDTO.getPermissions().forEach(e->{ nowPermissions.add(e.getId()); });

    // diff运算
    Set<Long> adds = new HashSet<>();
    Set<Long> removes = new HashSet<>();

    // 如果修改后的不包含原来的 那么为新增元素
    for(Long i:nowPermissions){
      if(!raw.contains(i)){
        adds.add(i);
      }
    }

    // 如果原来的不包含修改后的 那么是删除元素
    for(Long i:raw){
      if(!nowPermissions.contains(i)){
        removes.add(i);
      }
    }

    if(!adds.isEmpty()){
        this.roleService.savePermissions(roleWithPermissionDTO.getId(),new ArrayList<>(adds));
    }
    if(!removes.isEmpty()){
      removes.forEach(e->{
        this.roleService.deleteRolePermissionItem(roleWithPermissionDTO.getId(),e);
      });
    }
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
    IPage<RoleDO> rolePage = new Page<RoleDO>(page,size);
    rolePage = this.roleService.page(rolePage);
//    final PageInfo<RoleDO> pageInfo = new PageInfo<>(list);
    return ResultGenerator.genOkResult(rolePage);
  }
}
