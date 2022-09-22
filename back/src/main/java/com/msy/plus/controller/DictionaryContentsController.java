package com.msy.plus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.msy.plus.core.response.Result;
import com.msy.plus.core.response.ResultGenerator;
import com.msy.plus.entity.DictionaryContents;
import com.msy.plus.service.DictionaryContentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
* @author MoShuYing
* @date 2021/05/18
*/
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"数据字典接口"})
@RestController
@RequestMapping("/dictionary/contents")
public class DictionaryContentsController {
    @Resource
    private DictionaryContentsService dictionaryContentsService;

    @Operation(description = "数据字典添加")
    @PostMapping
    public Result add(@RequestBody DictionaryContents dictionaryContents) {
        dictionaryContentsService.save(dictionaryContents);
        return ResultGenerator.genOkResult();
    }

//    @Operation(description = "数据字典删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    dictionaryContentsService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }

    @Operation(description = "数据字典更新")
    @PutMapping
    public Result update(@RequestBody DictionaryContents dictionaryContents) {
    dictionaryContentsService.saveOrUpdate(dictionaryContents);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "数据字典获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    DictionaryContents dictionaryContents = dictionaryContentsService.getById(id);
        return ResultGenerator.genOkResult(dictionaryContents);
    }

    @Operation(description = "数据字典分页查询")
    @GetMapping
    @ApiOperation(value="分页查询数据字典", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer size,
                       @RequestParam(defaultValue = "null") String keyword) {
        String inKeyword = null;
        if (!(keyword == null || keyword.equals("null"))) {
            inKeyword = keyword;
        }
//        PageHelper.startPage(page, size);
        IPage<DictionaryContents> dictionaryContentsPage = new Page<DictionaryContents>(page, size);
        dictionaryContentsPage = dictionaryContentsService.listWithKeyword(dictionaryContentsPage, inKeyword);
//        PageInfo<DictionaryContents> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(dictionaryContentsPage);
    }
}
