package ${basePackage}.controller;

import ${basePackage}.core.response.Result;
import ${basePackage}.core.response.ResultGenerator;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
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
* @author ${author}
* @date ${date}
*/
@PreAuthorize("hasAuthority('ADMIN')")
@Api(tags={"生成接口"})
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @Operation(description = "生成添加")
    @PostMapping
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "生成删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "生成更新")
    @PutMapping
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
    ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "生成获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.getById(id);
        return ResultGenerator.genOkResult(${modelNameLowerCamel});
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
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.listAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
