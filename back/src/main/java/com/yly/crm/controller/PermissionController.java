package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.Permission;
import com.yly.crm.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @author MoShuYing
* @date 2021/05/14
*/
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"权限接口"})
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Resource
    private PermissionService permissionService;
//
//    @Operation(description = "权限添加")
//    @PostMapping
//    public Result add(@RequestBody Permission permission) {
//        permissionService.save(permission);
//        return ResultGenerator.genOkResult();
//    }

    @Operation(description = "权限删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    permissionService.removeById(id);
        return ResultGenerator.genOkResult();
    }

//    @Operation(description = "权限更新")
//    @PutMapping
//    public Result update(@RequestBody Permission permission) {
//    permissionService.update(permission);
//        return ResultGenerator.genOkResult();
//    }

//    @Operation(description = "权限获取详细信息")
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Long id) {
//    Permission permission = permissionService.getById(id);
//        return ResultGenerator.genOkResult(permission);
//    }

    @Operation(description = "权限分页查询")
    @GetMapping
    @ApiOperation(value="分页查询权限", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer size) {
//        PageHelper.startPage(page, size);
        IPage<Permission> ppage = new Page<Permission>(page, size);
        IPage<Permission> list = permissionService.page(ppage);
//        PageInfo<Permission> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(list);
    }
}
