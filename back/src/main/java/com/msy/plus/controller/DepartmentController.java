package com.msy.plus.controller;

import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.entity.Department;
import com.msy.plus.service.DepartmentService;
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
@PreAuthorize(
        "hasAuthority('ADMIN') " +
                "or hasAuthority('董事长') " +
                "or hasAuthority('主席') " +
                "or hasAuthority('高级主席') " +
                "or hasAuthority('副主席') " +
                "or hasAuthority('总裁') " +
                "or hasAuthority('会长') " +
                "or hasAuthority('高级总裁') " +
                "or hasAuthority('高级副总裁')")
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
        departmentService.removeById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "部门更新")
    @PatchMapping
    public Result update(@RequestBody Department department) {
        departmentService.saveOrUpdate(department);
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
//        PageHelper.startPage(page, size);
        List<Department> list = departmentService.list();
//        PageInfo<Department> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(list);
    }
}
