package com.msy.plus.controller;

import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.entity.DictionaryDetails;
import com.msy.plus.service.DictionaryDetailsService;
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
* @date 2021/05/18
*/
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"数据字典明细接口"})
@RestController
@RequestMapping("/dictionary/details")
public class DictionaryDetailsController {
    @Resource
    private DictionaryDetailsService dictionaryDetailsService;

    @Operation(description = "数据字典明细添加")
    @PostMapping
    public Result add(@RequestBody DictionaryDetails dictionaryDetails) {
        dictionaryDetailsService.save(dictionaryDetails);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "数据字典明细删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    dictionaryDetailsService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "数据字典明细更新")
    @PutMapping
    public Result update(@RequestBody DictionaryDetails dictionaryDetails) {
    dictionaryDetailsService.update(dictionaryDetails);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "数据字典明细获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    DictionaryDetails dictionaryDetails = dictionaryDetailsService.getById(id);
        return ResultGenerator.genOkResult(dictionaryDetails);
    }

    @Operation(description = "数据字典明细分页查询")
    @GetMapping
    @ApiOperation(value="分页查询数据字典明细", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<DictionaryDetails> list = dictionaryDetailsService.listAll();
        PageInfo<DictionaryDetails> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
