package ${basePackage}.controller;

import ${basePackage}.core.response.Result;
import ${basePackage}.core.response.ResultGenerator;
import ${basePackage}.entity.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "接口")
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @Operation(description = "添加")
    @PostMapping
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
    ${modelNameLowerCamel}Service.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "更新")
    @PutMapping
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
    ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
    ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.getById(id);
        return ResultGenerator.genOkResult(${modelNameLowerCamel});
    }

    @Operation(description = "分页查询")
    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page,
    @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.listAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = PageInfo.of(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
