package com.msy.plus.controller;

import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.entity.CustomerFollowUpHistory;
import com.msy.plus.service.CustomerFollowUpHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @author MoShuYing
* @date 2021/05/21
*/
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"客户跟进记录接口"})
@RestController
@RequestMapping("/customer/follow/up/history")
public class CustomerFollowUpHistoryController {
    @Resource
    private CustomerFollowUpHistoryService customerFollowUpHistoryService;

    @Operation(description = "客户跟进记录添加")
    @PostMapping
    public Result add(@RequestBody CustomerFollowUpHistory customerFollowUpHistory) {
        if(customerFollowUpHistory.getId()!=null){
            customerFollowUpHistory.setId(null);
        }
        customerFollowUpHistoryService.save(customerFollowUpHistory);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "客户跟进记录删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    customerFollowUpHistoryService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "客户跟进记录更新")
    @PutMapping
    public Result update(@RequestBody CustomerFollowUpHistory customerFollowUpHistory) {
    customerFollowUpHistoryService.update(customerFollowUpHistory);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "客户跟进记录获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    CustomerFollowUpHistory customerFollowUpHistory = customerFollowUpHistoryService.getById(id);
        return ResultGenerator.genOkResult(customerFollowUpHistory);
    }

    @Operation(description = "客户跟进记录分页查询")
    @GetMapping
    @ApiOperation(value="分页查询客户跟进记录", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<CustomerFollowUpHistory> list = customerFollowUpHistoryService.listAll();
        PageInfo<CustomerFollowUpHistory> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
