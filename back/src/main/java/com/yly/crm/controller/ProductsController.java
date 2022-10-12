package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.Products;
import com.yly.crm.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author : jiuyuehe in Administrator
 * 2022/10/11 16:00
 * @description :产品管理模块
 **/

@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"产品管理接口"})
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;


    @PostMapping
    private Result addProduct(@RequestBody Products prts){
        return ResultGenerator.genOkResult(productsService.saveOrUpdate(prts));
    }


    @Operation(description = "企业客户管理分页查询")
    @GetMapping("list")
    public Result productsList(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(defaultValue = "",required = false) String keyword,
                                         @RequestParam(required = false) Integer status){
        IPage<Products> cepage = new Page(page, size);

        LambdaQueryWrapper<Products> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Products::getProductName,keyword);

        cepage = productsService.page(cepage,queryWrapper);

        //CustomerEnterprise ce = customerEnterpriseService.getById(ceid);
        return ResultGenerator.genOkResult(cepage);
    }

}
