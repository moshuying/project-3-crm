package com.msy.plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msy.plus.core.jwt.JwtUtil;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.dto.CustomerHandoverList;
import com.msy.plus.entity.CustomerHandover;
import com.msy.plus.service.CustomerHandoverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author MoShuYing
* @date 2021/05/21
*/
@PreAuthorize(
        "hasAuthority('ADMIN')"+
        "or hasAuthority('董事长')"+
        "or hasAuthority('主席')"+
        "or hasAuthority('高级主席')"+
        "or hasAuthority('副主席')"+
        "or hasAuthority('总裁')"+
        "or hasAuthority('会长')"+
        "or hasAuthority('高级总裁')"+
        "or hasAuthority('高级副总裁')"+
        "or hasAuthority('副总裁')"+
        "or hasAuthority('总经理')"+
        "or hasAuthority('副总经理')"+
        "or hasAuthority('总监')"+
        "or hasAuthority('经理')"+
        "or hasAuthority('高级经理')"+
        "or hasAuthority('副经理')"+
        "or hasAuthority('主任')"+
        "or hasAuthority('高级主任')"+
        "or hasAuthority('副主任')"+
        "or hasAuthority('组长')"+
        "or hasAuthority('副组长')"+
        "or hasAuthority('普通员工')"+
        "or hasAuthority('人事专员')"+
        "or hasAuthority('市场专员')"+
        "or hasAuthority('市场主管')"+
        "or hasAuthority('销售主管')"
)
@Api(tags={"移交历史接口"})
@RestController
@RequestMapping("/customer/handover")
public class CustomerHandoverController {
    @Resource private CustomerHandoverService customerHandoverService;
    @Resource private JwtUtil jwtUtil;

    @Operation(description = "移交历史添加")
    @PostMapping
    public Result add(@RequestBody CustomerHandover customerHandover,@RequestHeader Map<String, String> headers) {
        if(customerHandover.getId()!=null){
            customerHandover.setId(null);
        }
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get();
        customerHandover.setTransuser(Integer.valueOf(id));
        customerHandoverService.save(customerHandover);
        return ResultGenerator.genOkResult();
    }

//    @Operation(description = "移交历史删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    customerHandoverService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }
//
//    @Operation(description = "移交历史更新")
//    @PutMapping
//    public Result update(@RequestBody CustomerHandover customerHandover) {
//    customerHandoverService.update(customerHandover);
//        return ResultGenerator.genOkResult();
//    }
//
//    @Operation(description = "移交历史获取详细信息")
//    @GetMapping("/{id}")
//    public Result detail(@PathVariable Long id) {
//    CustomerHandover customerHandover = customerHandoverService.getById(id);
//        return ResultGenerator.genOkResult(customerHandover);
//    }

    @Operation(description = "移交历史分页查询")
    @GetMapping
    @ApiOperation(value="分页查询移交历史", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
                       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime) {
        IPage<CustomerHandoverList> customerHandoverListIPage = new Page<CustomerHandoverList>(page, size);
        customerHandoverListIPage = customerHandoverService.listAndSearch(customerHandoverListIPage,keyword,startTime,endTime);
//        PageInfo<CustomerHandoverList> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(customerHandoverListIPage);
    }
}
