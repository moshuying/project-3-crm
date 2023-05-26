package com.yly.crm.controller;


// 客户企业信息管理控制

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.CustomerEnterprise;
import com.yly.crm.service.CustomerEnterpriseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jiuyuehe
 *
 * @descript 企业管理接口，企业主要分为：客户，代理商；
 * 同时企业客户又分为成交与未成交客户，成交过的客户要有独立的标志；
 * 企业客户要有日志记录，针对这个客户干了什么，是谁干的；
 * @date 2022-10-09
 */
@Slf4j
@RestController
@RequestMapping("/customer/enterprise")
public class CustomerEnterpriseController {

    @Autowired
    private CustomerEnterpriseService customerEnterpriseService;
    @Resource
    private JwtUtil jwtUtil;

    @Operation(description = "企业客户新增与更新，更新确保传id，否则为新增")
    @PostMapping
    public Result addCusEnt(@RequestBody CustomerEnterprise ce ,@RequestHeader Map<String, String> headers){
        log.debug("addCusEnt {}, params {}",ce,headers);
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get(); // 取得用户id
        String name= jwtUtil.getName(headers.get(header)).get(); // 取得用户用户名
        log.info("id {},  name {}",id, name);
        ce.setEntBelongBizerId(Integer.parseInt(id));
        ce.setEntBelongBizerName(name);
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
        queryWrapper.orderByDesc("ent_id");


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
