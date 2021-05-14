package com.zoctan.seedling.controller;

import com.zoctan.seedling.core.response.Result;
import com.zoctan.seedling.core.response.ResultGenerator;
import com.zoctan.seedling.entity.Department;
import com.zoctan.seedling.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @author MoShuYing
 * @date 2021/05/12
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"部门接口"})
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @Operation(description = "部门添加")
    @PostMapping
    public Result add(@RequestBody Department department) {
        departmentService.save(department);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "部门删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        departmentService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "部门更新")
    @PatchMapping
    public Result update(@RequestBody Department department) {
        departmentService.update(department);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "获取部门详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Department department = departmentService.getById(id);
        return ResultGenerator.genOkResult(department);
    }

    @Operation(description = "分页查询部门")
    @GetMapping
    @ApiOperation(value="分页查询部门", notes="分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
            @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.listAll();
        PageInfo<Department> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
