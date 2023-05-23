package com.yly.crm.controller;


// 自定业务数据库

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.CustomerEnterprise;
import com.yly.crm.entity.DictionaryTableBizRef;
import com.yly.crm.service.DictionaryTableBizRefService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jiuyuehe
 *
 * @descript 自定义业务数据库，主要自定义企业、联系人等有业务表的业务字段扩展；
 * @date 2023-05-23
 */
@Slf4j
@RestController
@RequestMapping("/dic/bizref")
public class DicTableBizRefController {

    @Autowired
    private DictionaryTableBizRefService dictionaryTableBizRefService;
    @Resource
    private JwtUtil jwtUtil;

    @Operation(description = "新增业务表")
    @PostMapping
    public Result addDicBizTableRef(@RequestBody DictionaryTableBizRef dtref , @RequestHeader Map<String, String> headers){

        log.debug("DictionaryTableBizRef {}, DictionaryTableBizRef {}",dtref,headers);
        boolean success = dictionaryTableBizRefService.saveOrUpdate(dtref);
        return ResultGenerator.genOkResult(success);
    }


    @Operation(description = "获取详情")
    @GetMapping("{refId}")
    public Result getDicBizTableRef(@PathVariable Long refId){
        DictionaryTableBizRef ce = dictionaryTableBizRefService.getById(refId);
        return ResultGenerator.genOkResult(ce);
    }

    @Operation(description = "分页查询业务字段关系管理")
    @GetMapping("list")
    public Result pageDicBizRefList(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(defaultValue = "",required = false) String keyword,
                                         @RequestParam(required = false) Integer status){
        IPage<DictionaryTableBizRef> cepage = new Page(page, size);

        QueryWrapper<DictionaryTableBizRef> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        cepage =  dictionaryTableBizRefService.page(cepage,queryWrapper);
        return ResultGenerator.genOkResult(cepage);
    }

    @Operation(description = "删除业务字段关系")
    @DeleteMapping
    public Result delDicBizRef(@PathVariable Long id){

        boolean su = dictionaryTableBizRefService.removeById(id);
        return ResultGenerator.genOkResult(su);
    }

}
