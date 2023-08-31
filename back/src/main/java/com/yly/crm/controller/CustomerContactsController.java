package com.yly.crm.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.dto.CustomerContactsDTO;
import com.yly.crm.entity.CustomerContacts;
import com.yly.crm.service.CustomerContactsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

/**
* @author jiuyuehe
* @date 2023/05/06
 * 联系人接口， 联系人都要挂到企业下面，联系人可以移动到其他企业，联系人要有职务跟联系方式；
 * 后面要给联系人简历一些档案；
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
@Api(tags={"客户管理接口"})
@RestController
@RequestMapping("/customer/manager")
public class CustomerContactsController {
    @Resource private CustomerContactsService customerContactsService;
    @Resource private JwtUtil jwtUtil;

    @Operation(description = "客户管理添加")
    @PostMapping
    public Result add(@RequestBody CustomerContacts customerContacts, @RequestHeader Map<String, String> headers) {
        if(customerContacts.getId()!=null){
            customerContacts.setId(null);
        }
        String header = jwtUtil.getJwtProperties().getHeader();
        String id= jwtUtil.getId(headers.get(header)).get();
        customerContacts.setInputuser(Integer.valueOf(id));
        customerContacts.setSeller(Integer.valueOf(id));
        customerContactsService.save(customerContacts);
        return ResultGenerator.genOkResult();
    }

//    @Operation(description = "客户管理删除")
//    @DeleteMapping("/{id}")
//    public Result delete(@PathVariable Long id) {
//    customerManagerService.deleteById(id);
//        return ResultGenerator.genOkResult();
//    }

    @Operation(description = "客户管理更新")
    @PutMapping
    public Result update(@RequestBody CustomerContacts customerContacts) {
    customerContactsService.saveOrUpdate(customerContacts);
        return ResultGenerator.genOkResult();
    }

    @Operation(description = "客户管理获取详细信息")
    @GetMapping("/{id}")
    public Result detail(@PathVariable Long id) {
        CustomerContactsDTO customerContactsDTO = customerContactsService.getDetailById(id);
        return ResultGenerator.genOkResult(customerContactsDTO);
    }

    @Operation(description = "客户管理分页查询")
    @GetMapping
    @ApiOperation(value="分页查询客户管理", notes="分页查询 ")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "Integer", paramType="query"),
        @ApiImplicitParam(name = "size", value = "一页有几条", required = true, dataType = "Integer", paramType="query")
    })
    public Result list(@RequestParam(defaultValue = "1") Integer page,
       @RequestParam(defaultValue = "10") Integer size,
       @RequestParam(defaultValue = "",required = false) String keyword,
       @RequestParam(required = false) Integer status,
       @RequestParam(required = false) Integer ceId) {
        IPage<CustomerContactsDTO> customerManagerListIPage = new Page<CustomerContactsDTO>(page, size);

        customerManagerListIPage = customerContactsService.listAllWithDictionary(customerManagerListIPage,keyword,status,ceId);
        return ResultGenerator.genOkResult(customerManagerListIPage);
    }
}
