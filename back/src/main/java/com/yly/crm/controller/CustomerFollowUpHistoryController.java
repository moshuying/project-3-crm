package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.dto.CustomerFollowUpHistoryDTO;
import com.yly.crm.entity.CustomerFollowUpHistory;
import com.yly.crm.service.CustomerFollowUpHistoryService;
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
import java.util.Map;

/**
* @author jiuyuehe
* @date 2022/05/06
 * @descript 客户跟进；主要跟进对象为企业客户，子对象为某个项目跟某个人，内容为跟进的内容；跟进后的判断是什么？
*/
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"客户跟进记录接口"})
@RestController
@RequestMapping("/customer/follow/up/history")
public class CustomerFollowUpHistoryController {
    @Resource private CustomerFollowUpHistoryService customerFollowUpHistoryService;
    @Resource private JwtUtil jwtUtil;
    @Operation(description = "客户跟进记录添加")
    @PostMapping
    public Result add(@RequestBody CustomerFollowUpHistory customerFollowUpHistory,@RequestHeader Map<String, String> headers) {
        if(customerFollowUpHistory.getId()!=null){
            customerFollowUpHistory.setId(null);
        }
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get();
        customerFollowUpHistory.setInputuser(Integer.valueOf(id));
        customerFollowUpHistoryService.save(customerFollowUpHistory);
        return ResultGenerator.genOkResult();
    }

//    @Operation(description = "客户跟进记录删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    customerFollowUpHistoryService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }

    @Operation(description = "客户跟进记录更新")
    @PutMapping
    public Result update(@RequestBody CustomerFollowUpHistory customerFollowUpHistory) {
    customerFollowUpHistoryService.saveOrUpdate(customerFollowUpHistory);
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
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "keyword", value = "内容关键字", required = false, dataType = "String", paramType="query"),
        @ApiImplicitParam(name = "customerEntId", value = "企业id", required = false, dataType = "Long", paramType="query"),
        @ApiImplicitParam(name = "type", value = "跟进类型", required = false, dataType = "Integer", paramType="query")
    })
    public Result list(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "") Long customerEntId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date endTime,
            @RequestParam(required = false) Integer type) {

        IPage<CustomerFollowUpHistoryDTO> qpage = new Page<CustomerFollowUpHistoryDTO>(page, size);
        qpage= customerFollowUpHistoryService.listAndSearch(qpage,keyword,customerEntId,startTime,endTime,type);
        return ResultGenerator.genOkResult(qpage);
    }
}
