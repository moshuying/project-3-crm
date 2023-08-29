package com.yly.crm.controller;


import com.yly.crm.entity.Baobei;
import com.yly.crm.mapper.BaobeiMapper;
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
    public BaobeiMapper baobeiMapper;


    @PostMapping("/add")
    public boolean addBaobei(@RequestBody Baobei baobei){
         return  true;
     // return   BaobeiEntity.create().setProductName("云盘").setProductId(2L).setCustomerName("申浪").setProjectDesc("asdfasdf").save();
    }


//    @GetMapping
//    @ResponseBody
//    public List<BaobeiEntity> getAllBaobei(){
//        return baobeiService.listAllAndSearch("ts",null,null,0);
//
//    }

}
