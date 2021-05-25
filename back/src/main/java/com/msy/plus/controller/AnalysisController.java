package com.msy.plus.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msy.plus.core.jwt.JwtUtil;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.dto.AnalysisQuery;
import com.msy.plus.dto.RoleWithPermissionDTO;
import com.msy.plus.entity.*;
import com.msy.plus.service.CustomerManagerService;
import com.msy.plus.service.EmployeeService;
import com.msy.plus.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author MoShuYing
 * @date 2021/05/15
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
@Api(tags={"统计分析接口"})
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Resource CustomerManagerService customerManagerService;
    @Resource EmployeeService employeeService;
    @Resource private RoleService roleService;
    @Resource private JwtUtil jwtUtil;
    @Operation(description = "统计分析")
    @PostMapping
    public Result listAndSearch(@RequestBody AnalysisQuery analysisQuery,@RequestHeader Map<String, String> headers) {
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get();
        List<Long> roleIds = employeeService.getDetailById(Integer.valueOf(id).longValue()).getRoleIds();
        for(Long roleId:roleIds){
            RoleWithPermissionDO roleWithPermissionDO = roleService.getDetailById(roleId);
            if(roleWithPermissionDO==null || roleWithPermissionDO.getPermissions()==null ||  roleWithPermissionDO.getPermissions().isEmpty()){
                return ResultGenerator.genFailedResult("无法获取角色权限！");
            }
            List<Permission> permissionList = roleWithPermissionDO.getPermissions();
            for(Permission permission:permissionList){
                if(permission.getName().equals("董事长")){
                    PageHelper.startPage(analysisQuery.getPage(),analysisQuery.getSize());
                    PageInfo<Analysis> pageInfo = PageInfo.of(customerManagerService.queryAnalysis(analysisQuery));
                    return ResultGenerator.genOkResult(pageInfo);
                }
            }
        }
        // 除了董事长 其他人都只能查看自己的
        analysisQuery.setName(jwtUtil.getName(headers.get(header)).get());
        PageHelper.startPage(analysisQuery.getPage(),analysisQuery.getSize());
        PageInfo<Analysis> pageInfo = PageInfo.of(customerManagerService.queryAnalysis(analysisQuery));
        return ResultGenerator.genOkResult(pageInfo);
    }

}
