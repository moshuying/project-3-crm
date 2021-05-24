package com.msy.plus.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.dto.AnalysisQuery;
import com.msy.plus.entity.Analysis;
import com.msy.plus.entity.CFUHSearch;
import com.msy.plus.service.CustomerManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource
    CustomerManagerService customerManagerService;

    @Operation(description = "统计分析")
    @PostMapping
    public Result listAndSearch(@RequestBody AnalysisQuery analysisQuery) {
        PageHelper.startPage(analysisQuery.getPage(),analysisQuery.getSize());
        PageInfo<Analysis> pageInfo = PageInfo.of(customerManagerService.queryAnalysis(analysisQuery));
        return ResultGenerator.genOkResult(pageInfo);
    }

}
