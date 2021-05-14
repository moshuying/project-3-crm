package com.zoctan.seedling.controller;

import com.zoctan.seedling.core.response.Result;
import com.zoctan.seedling.core.response.ResultGenerator;
import com.zoctan.seedling.entity.Department;
import com.zoctan.seedling.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MoShuYing
 * @date 2021/05/12
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "部门接口")
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
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Department> list = departmentService.listAll();
        PageInfo<Department> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
