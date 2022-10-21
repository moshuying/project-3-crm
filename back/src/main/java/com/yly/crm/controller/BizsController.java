package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.Bizs;
import com.yly.crm.entity.Products;
import com.yly.crm.service.BizsService;
import com.yly.crm.service.CustomerManagerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : jiuyuehe in Administrator
 * 2022/10/10 19:42
 * @description :
 **/


@RestController
@RequestMapping("/bizs")
public class BizsController {


    @Autowired
    private BizsService bizsService;
    @Autowired
    private CustomerManagerService customerManagerService;

    @Operation(description = "添加商机, 新增接口不做鉴权处理")
    @PostMapping
    public Result<Bizs> addBiz(@RequestBody Bizs bizs, @RequestHeader Map<String, String> headers){
        System.out.println(bizs);
        System.out.println(headers);
        bizsService.save(bizs);
        return  ResultGenerator.genOkResult();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(description = "获取商机列表, 新增接口不做鉴权处理")
    @PostMapping
    public Result  listBizs(@RequestParam(defaultValue = "1") Integer page,
                            @RequestParam(defaultValue = "10") Integer size,
                            @RequestParam(defaultValue = "",required = false) String keyword,
                            @RequestParam(required = false) Integer status,@RequestHeader Map<String, String> headers){

        IPage<Bizs> bizsIPage = new Page<>(page, size);
        bizsIPage =  bizsService.page(bizsIPage, Wrappers.lambdaQuery(Bizs.class).like(Bizs::getBizDesc,keyword));
        return ResultGenerator.genOkResult(bizsIPage);
    }

}
