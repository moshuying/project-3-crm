package com.yly.crm.controller;

import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.Bizs;
import com.yly.crm.service.BizsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Operation(description = "添加商机, 新增接口不做鉴权处理")
    @PostMapping
    public Result<Bizs> addBiz(@RequestBody Bizs bizs, @RequestHeader Map<String, String> headers){
        System.out.println(bizs);
        System.out.println(headers);
        bizsService.save(bizs);
        return  ResultGenerator.genOkResult();
    }

}
