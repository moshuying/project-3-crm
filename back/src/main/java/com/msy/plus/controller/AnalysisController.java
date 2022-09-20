package com.msy.plus.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msy.plus.core.jwt.JwtUtil;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.dto.AnalysisQuery;
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
@Api(tags={"统计分析接口"})
@RestController
@RequestMapping("/analysis")
public class AnalysisController {
    @Resource CustomerManagerService customerManagerService;
    @Resource EmployeeService employeeService;
    @Resource RoleService roleService;
    @Resource private JwtUtil jwtUtil;
    @Operation(description = "统计分析")
    @PostMapping
    public Result listAndSearch(@RequestBody AnalysisQuery analysisQuery,@RequestHeader Map<String, String> headers) {
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get();
        List<Long> roleIds = employeeService.getDetailById(Integer.valueOf(id).longValue()).getRoleIds();
        for(Long roleId:roleIds){
            RoleWithPermissionDO  roleWithPermissionDO =  roleService.getDetailById(roleId);
            if(roleWithPermissionDO==null) {
                continue;
            }
            String roleName = roleWithPermissionDO.getName();
            if(roleName==null || roleName.isEmpty()){
                continue;
            }
            if(roleName.equals("董事长")){
                IPage qpage = new Page(analysisQuery.getPage(),analysisQuery.getSize());
               // PageInfo<Analysis> pageInfo = PageInfo.of(customerManagerService.queryAnalysis(analysisQuery));
                List<Analysis>  analysesList = customerManagerService.queryAnalysis(analysisQuery);
                return ResultGenerator.genOkResult(analysesList);
            }
        }
        // 除了董事长 其他人都只能查看自己的
        analysisQuery.setName(jwtUtil.getName(headers.get(header)).get());
        IPage qpage = new Page(analysisQuery.getPage(),analysisQuery.getSize());
        List<Analysis>  analysesList = customerManagerService.queryAnalysis(analysisQuery);
        return ResultGenerator.genOkResult(analysesList);
    }

}
