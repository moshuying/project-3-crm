package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.Bizs;
import com.yly.crm.entity.Products;
import com.yly.crm.service.ProductsService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : jiuyuehe in Administrator
 * 2022/10/11 16:00
 * @description :产品管理模块
 **/

@Api(tags={"产品管理接口"})
@PreAuthorize("hasAuthority('ADMIN')")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {


    private final ProductsService productsService;


    @Operation(description = "(共用)新增产品/编辑接口,传输product 对象主要名称,传id为更新,不传id为新增")
    @PostMapping
    public Result<Products> addProduct(@RequestBody Products product, @RequestHeader Map<String, String> headers){
        System.out.println(headers);
        productsService.saveOrUpdate(product);
        return  ResultGenerator.genOkResult();
    }


    @Operation(description = "删除产品接口, 其中id为必须传.")
    @DeleteMapping
    public Result<Products> delProduct(@RequestBody Products product, @RequestHeader Map<String, String> headers){
        System.out.println(headers);
        productsService.removeById(product);
        return  ResultGenerator.genOkResult();
    }


    @Operation(description = "企业客户管理分页查询")
    @GetMapping("list")
    public Result productsList(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(defaultValue = "",required = false) String keyword,
                                         @RequestParam(required = false) Integer status){
        IPage<Products> cepage = new Page(page, size);

        cepage = productsService.page(cepage,
                Wrappers.lambdaQuery(Products.class).like(Products::getProductName,keyword));

        //CustomerEnterprise ce = customerEnterpriseService.getById(ceid);
        return ResultGenerator.genOkResult(cepage);
    }

}
