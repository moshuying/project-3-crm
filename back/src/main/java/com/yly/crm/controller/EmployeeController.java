package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.Employee;
import com.yly.crm.entity.EmployeeDetail;
import com.yly.crm.entity.EmployeeWithRoleDO;
import com.yly.crm.service.EmployeeService;
import com.yly.crm.util.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Resource private JwtUtil jwtUtil;

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
        employeeService.removeById(id);
        employeeService.deleteEmployeeWithRole(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "员工更新")
    @PutMapping
    public Result update(@RequestBody EmployeeDetail employee,@RequestHeader Map<String, String> headers) {
        if(employee.getName().equals("admin")){
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
            employeeService.saveOrUpdate((Employee) employee);
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
        // 更新权限即注销对应用户登录
        if(!adds.isEmpty() || !removes.isEmpty()){
            jwtUtil.invalidRedisToken(employee.getName());
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
//        PageHelper.startPage(page, size);

        IPage<EmployeeWithRoleDO> ewro = new Page<EmployeeWithRoleDO>(page, size);

        IPage<EmployeeWithRoleDO> list = employeeService.listEmployeeWithRole(ewro, keyword, dept);

        System.out.println(list);

        // 不显示 password 字段
      //  final IPage<JSONObject> objectPageInfo = JsonUtils.deleteFields(ewro, IPage.class, "password");
        return ResultGenerator.genOkResult(list);
    }
}
