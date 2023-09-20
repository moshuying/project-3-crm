package com.yly.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.dto.AnalysisQuery;
import com.yly.crm.service.CustomerContactsService;
import com.yly.crm.service.EmployeeService;
import com.yly.crm.service.RoleService;
import com.yly.crm.entity.Analysis;
import com.yly.crm.entity.RoleWithPermissionDO;
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
    @Resource
    CustomerContactsService customerContactsService;
    @Resource
    EmployeeService employeeService;
    @Resource
    RoleService roleService;
    @Resource private JwtUtil jwtUtil;
    @Operation(description = "统计分析")
    @PostMapping
    public Result listAndSearch(@RequestBody AnalysisQuery analysisQuery, @RequestHeader Map<String, String> headers) {
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get();
        List<Long> roleIds = employeeService.getDetailById(Integer.valueOf(id).longValue()).getRoleIds();

        IPage<Analysis>  analysisIPage = new Page<Analysis> (analysisQuery.getPage(),analysisQuery.getSize());

        for(Long roleId:roleIds){
            RoleWithPermissionDO roleWithPermissionDO =  roleService.getDetailById(roleId);
            if(roleWithPermissionDO==null) {
                continue;
            }
            String roleName = roleWithPermissionDO.getName();
            if(roleName==null || roleName.isEmpty()){
                continue;
            }
            if(roleName.equals("董事长")){
               // PageInfo<Analysis> pageInfo = PageInfo.of(customerManagerService.queryAnalysis(analysisQuery));
                analysisIPage = customerContactsService.queryAnalysis(analysisIPage, analysisQuery);
                return ResultGenerator.genOkResult(analysisIPage);
            }
        }
        // 除了董事长 其他人都只能查看自己的
        analysisQuery.setName(jwtUtil.getName(headers.get(header)).get());
        analysisIPage = customerContactsService.queryAnalysis(analysisIPage, analysisQuery);
        return ResultGenerator.genOkResult(analysisIPage);
    }

}
