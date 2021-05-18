package com.msy.plus.controller;

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
@Api(tags={"生成接口"})
@RestController
@RequestMapping("/dictionary/contents")
public class DictionaryContentsController {
    @Resource
    private DictionaryContentsService dictionaryContentsService;

    @Operation(description = "生成添加")
    @PostMapping
    public Result add(@RequestBody DictionaryContents dictionaryContents) {
        dictionaryContentsService.save(dictionaryContents);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "生成删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    dictionaryContentsService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "生成更新")
    @PutMapping
    public Result update(@RequestBody DictionaryContents dictionaryContents) {
    dictionaryContentsService.update(dictionaryContents);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "生成获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    DictionaryContents dictionaryContents = dictionaryContentsService.getById(id);
        return ResultGenerator.genOkResult(dictionaryContents);
    }

    @Operation(description = "生成分页查询")
    @GetMapping
    @ApiOperation(value="分页查询生成", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
    @RequestParam(defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        List<DictionaryContents> list = dictionaryContentsService.listAll();
        PageInfo<DictionaryContents> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
