package com.msy.plus.controller;

import com.alibaba.fastjson.JSONObject;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.entity.Employee;
import com.msy.plus.entity.EmployeeDetail;
import com.msy.plus.entity.EmployeeWithRoleDO;
import com.msy.plus.service.EmployeeService;
import com.msy.plus.util.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/15
*/
@PreAuthorize(
        "hasAuthority('ADMIN')"+
                "or hasAuthority('董事长')"+
                "or hasAuthority('主席')"+
                "or hasAuthority('高级主席')"+
                "or hasAuthority('副主席')"+
                "or hasAuthority('总裁')"+
                "or hasAuthority('会长')"+
                "or hasAuthority('高级总裁')"+
                "or hasAuthority('高级副总裁')"+
                "or hasAuthority('副总裁')"+
                "or hasAuthority('总经理')"+
                "or hasAuthority('副总经理')"+
                "or hasAuthority('总监')"+
                "or hasAuthority('经理')"+
                "or hasAuthority('高级经理')"+
                "or hasAuthority('副经理')"+
                "or hasAuthority('主任')"+
                "or hasAuthority('高级主任')"+
                "or hasAuthority('副主任')"+
                "or hasAuthority('组长')"+
                "or hasAuthority('副组长')"+
                "or hasAuthority('普通员工')"+
                "or hasAuthority('人事专员')"+
                "or hasAuthority('市场专员')"+
                "or hasAuthority('市场主管')"+
                "or hasAuthority('销售主管')"
)
@Api(tags={"员工接口"})
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;
    @Resource private PasswordEncoder passwordEncoder;

    @Operation(description = "员工添加")
    @PostMapping
    public Result add(@RequestBody EmployeeDetail employee) {
        if (employee.getId()!=null){
            employee.setId(null);
        }
        if(employee.getDept() ==null){
            return ResultGenerator.genFailedResult("请填写员工部门信息");
        }
        if(employee.getPassword()!=null && employee.getPassword().length()<=5){
            return ResultGenerator.genFailedResult("密码长度不能少于或等于五位");
        }
        employee.setPassword(this.passwordEncoder.encode(employee.getPassword().trim()));
        try{
            employeeService.save(employee);
        }catch (Exception e){
            e.printStackTrace();
            String msg = "信息有误";
            if(e.toString().contains("for key 'employee.employee_name_uindex'")){
                msg = "已有同名员工，请检查员工名称";
            }else if(e.toString().contains("for key 'employee.employee_email_uindex'")){
                msg = "已有同名邮箱，请检查员工邮箱";
            }
            return ResultGenerator.genFailedResult(msg);
        }
        if(!(employee.getRoleIds() ==null || employee.getRoleIds().size()<1)){
            employeeService.saveRoles(employee.getId(),employee.getRoleIds());
        }
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        employeeService.deleteEmployeeWithRole(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工更新")
    @PutMapping
    public Result update(@RequestBody EmployeeDetail employee) {
        if(employee.getName().equals("ADMIN")){
            return ResultGenerator.genFailedResult("禁止修改管理员角色！");
        }
        // 更新员工基本信息
        if(employee.getDept() ==null){
            return ResultGenerator.genFailedResult("请填写员工部门信息");
        }
        if(employee.getPassword()!=null){
            if(employee.getPassword().length()<=5){
                return ResultGenerator.genFailedResult("密码长度不能少于或等于五位");
            }
            employee.setPassword(this.passwordEncoder.encode(employee.getPassword().trim()));
        }
        try{
            employeeService.update((Employee) employee);
        }catch (Exception e){
            e.printStackTrace();
            String msg = "信息有误";
            if(e.toString().contains("for key 'employee.employee_name_uindex'")){
                msg = "已有同名员工，请检查员工名称";
            }else if(e.toString().contains("for key 'employee.employee_email_uindex'")){
                msg = "已有同名邮箱，请检查员工邮箱";
            }
            return ResultGenerator.genFailedResult(msg);
        }
        List<Long> now= employee.getRoleIds();
        if(now==null) {
            return ResultGenerator.genOkResult();
        }
        List<Long> raw = this.employeeService.getAllEmployeeRoleTableRow(employee.getId());
        // diff运算
        List<Long> adds = new ArrayList<>();
        List<Long> removes = new ArrayList<>();
        for(Long i:now){
            if(!raw.contains(i)){
                adds.add(i);
            }
        }
        for(Long i:raw){
            if(!now.contains(i)){
                removes.add(i);
            }
        }
        if(!adds.isEmpty()){
            this.employeeService.saveRoles(employee.getId(),adds);
        }
        if(!removes.isEmpty()){
            for(Long i :removes){
                this.employeeService.deleteEmployeeWithRoleItem(employee.getId(),i);
            }
        }

        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        EmployeeDetail employee = employeeService.getDetailById(id);
        final EmployeeDetail object = JsonUtils.deleteFields(employee, EmployeeDetail.class, "password");
        return ResultGenerator.genOkResult(object);
    }

    @Operation(description = "员工分页查询")
    @GetMapping
    @ApiOperation(value="分页查询员工", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer dept,
            @RequestParam(defaultValue = "") String keyword) {
        PageHelper.startPage(page, size);
        List<EmployeeWithRoleDO> list = employeeService.listEmployeeWithRole(keyword, dept);
        PageInfo<EmployeeWithRoleDO> pageInfo = PageInfo.of(list);
        // 不显示 password 字段
        final PageInfo<JSONObject> objectPageInfo = JsonUtils.deleteFields(pageInfo, PageInfo.class, "password");
        return ResultGenerator.genOkResult(objectPageInfo);
    }
}
