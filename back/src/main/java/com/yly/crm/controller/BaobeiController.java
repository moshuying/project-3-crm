package com.yly.crm.controller;


import com.yly.crm.entity.Baobei;
import com.yly.crm.service.BaobeiService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Api(tags={"渠道报备接口"})
@Validated
@RestController
@RequestMapping("/baobei")
public class BaobeiController {


    @Autowired
    public BaobeiService baobeiService;

    @PostMapping("/add")
    public void addBaobei(Baobei baobei){
       // return baobeiService.save(baobei);
    }


    @GetMapping
    @ResponseBody
    public List<Baobei> getAllBaobei(){
        return baobeiService.listAllAndSearch("ts",null,null,0);

    }

}
