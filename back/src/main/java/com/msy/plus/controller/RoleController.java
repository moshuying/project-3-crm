package com.msy.plus.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.dto.RoleDTO;
import com.msy.plus.dto.RoleWithPermissionDTO;
import com.msy.plus.entity.Permission;
import com.msy.plus.entity.RoleDO;
import com.msy.plus.entity.RolePermissionDO;
import com.msy.plus.entity.RoleWithPermissionDO;
import com.msy.plus.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
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
  public Result update(@RequestBody final RoleWithPermissionDTO roleWithPermissionDTO) {
    // 更新用户基本信息
    this.roleService.update((RoleDO) roleWithPermissionDTO);

    List<RolePermissionDO> raw = this.roleService.getAllRolePermissionTableRow(roleWithPermissionDTO.getId());

    List<Long> nowPermissions = new ArrayList<>();
    roleWithPermissionDTO.getPermissions().forEach(e->{ nowPermissions.add(e.getId()); });

    // diff运算
      Set<Long> adds = new HashSet<>();
    Set<Long> removes = new HashSet<>();
    // 表中权限信息去重
    Set<Long> temp = new HashSet<>();
    for(RolePermissionDO e: raw){
      temp.add(e.getPermission_id());
    }

    // 中间表信息去重 （鲁棒性）
    for(int i=0;i<raw.size()-1;i++){
      for(int j=i+1;j<raw.size()-1;j++){
        if(raw.get(i).getPermission_id().equals(raw.get(j).getPermission_id())){
          removes.add(raw.get(j).getId());
        }
      }
    }

    for(Long e:removes){ this.deleteItem(e,raw); }

    // 如果修改后的不包含原来的 那么为新增元素
    for(Long i:nowPermissions){
      if(!temp.contains(i)){
        adds.add(i);
      }
    }

    // 如果原来的不包含修改后的 那么是删除元素
    for(Long i:temp){
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
  private void deleteItem(Long e,List<RolePermissionDO> raw){
    for(int i = 0; i<raw.size()-1; i++) {
      if(raw.get(i).getId().equals(e)){
        raw.remove(i);
        return;
      }
    }
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
