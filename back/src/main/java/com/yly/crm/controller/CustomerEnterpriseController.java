package com.yly.crm.controller;


// 客户企业信息管理控制

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.CustomerEnterprise;
import com.yly.crm.service.CustomerEnterpriseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiuyuehe
 *
 *
 * @date 2022-10-09
 */

@RestController
@RequestMapping("/customer/enterprise")
public class CustomerEnterpriseController {

    @Autowired
    private CustomerEnterpriseService customerEnterpriseService;

    @Operation(description = "企业客户新增与更新，更新确保传id，否则为新增")
    @PostMapping
    public Result addCusEnt(@RequestBody CustomerEnterprise ce){
        boolean success = customerEnterpriseService.saveOrUpdate(ce);
        return ResultGenerator.genOkResult(success);
    }


    @GetMapping("{ceid}")
    public Result getCustomerEnterpriseById(@PathVariable Long ceid){
        CustomerEnterprise ce = customerEnterpriseService.getById(ceid);
        return ResultGenerator.genOkResult(ce);
    }

    @Operation(description = "企业客户管理分页查询")
    @GetMapping("list")
    public Result CustomerEnterpriseList(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size,
                                         @RequestParam(defaultValue = "",required = false) String keyword,
                                         @RequestParam(required = false) Integer status){
        IPage<CustomerEnterprise> cepage = new Page(page, size);

        QueryWrapper<CustomerEnterprise> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("ent_name",keyword);

        cepage =  customerEnterpriseService.page(cepage,queryWrapper);

        //CustomerEnterprise ce = customerEnterpriseService.getById(ceid);
        return ResultGenerator.genOkResult(cepage);
    }

    @Operation(description = "删除企业客户")
    @DeleteMapping
    public Result CustomerEnterpriseList(@PathVariable Long ceid){


        // customerEnterpriseService
        CustomerEnterprise ce = customerEnterpriseService.getById(ceid);
        //ce.setEnt
        boolean su = customerEnterpriseService.saveOrUpdate(ce);

        //CustomerEnterprise ce = customerEnterpriseService.getById(ceid);
        return ResultGenerator.genOkResult(su);
    }

}
