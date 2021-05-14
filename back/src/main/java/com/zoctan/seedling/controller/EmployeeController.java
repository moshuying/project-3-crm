package com.zoctan.seedling.controller;

import com.zoctan.seedling.core.response.Result;
import com.zoctan.seedling.core.response.ResultGenerator;
import com.zoctan.seedling.entity.Employee;
import com.zoctan.seedling.service.EmployeeService;
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
 * @date 2021/05/13
 */
@PreAuthorize("hasAuthority('ADMIN')")
@Tag(name = "员工接口")
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
    public Result list(@RequestParam(defaultValue = "0") Integer page,
                       @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Employee> list = employeeService.listAll();
        PageInfo<Employee> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
