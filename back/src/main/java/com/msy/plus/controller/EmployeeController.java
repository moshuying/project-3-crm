package com.msy.plus.controller;

import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.entity.Employee;
import com.msy.plus.service.EmployeeService;
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
 * @date 2021/05/13
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"员工接口"})
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    @Operation(description = "员工添加")
    @PostMapping
    public Result add(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工更新")
    @PatchMapping
    public Result update(@RequestBody Employee employee) {
        employeeService.update(employee);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工信息细节")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return ResultGenerator.genOkResult(employee);
    }

    @Operation(description = "分页查询员工")
    @GetMapping
    @ApiOperation(value="分页查询员工", notes="分页查询 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
            @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<Employee> list = employeeService.listAll();
        PageInfo<Employee> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
