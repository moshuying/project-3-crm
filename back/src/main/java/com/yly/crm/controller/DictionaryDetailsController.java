package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.entity.DictionaryContents;
import com.yly.crm.entity.DictionaryDetails;
import com.yly.crm.service.DictionaryDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    dictionaryDetailsService.removeById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "数据字典明细更新")
    @PutMapping
    public Result update(@RequestBody DictionaryDetails dictionaryDetails) {
    dictionaryDetailsService.saveOrUpdate(dictionaryDetails);
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
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "1") Integer id,
                       @RequestParam(defaultValue = "null") String keyword) {
        String inKeyword = null;
        if (!(keyword == null || keyword.equals("null"))) {
            inKeyword = keyword;
        }
        Long inId = Long.valueOf(id);
        if(inId==null){
            inId = dictionaryDetailsService.list().get(0).getId();
        }
//        PageHelper.startPage(page, size);
        IPage<DictionaryContents> dictionaryContentsIPage =  new Page<DictionaryContents>(page,size);
        dictionaryContentsIPage = dictionaryDetailsService.listWithKeyword(dictionaryContentsIPage,inId.intValue(),inKeyword);
//        PageInfo<DictionaryContents> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(dictionaryContentsIPage);
    }
}
