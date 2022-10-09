package com.yly.crm.controller;

import com.yly.crm.core.jwt.JwtUtil;
import com.yly.crm.core.response.Result;
import com.yly.crm.core.response.ResultGenerator;
import com.yly.crm.dto.AccountDTO;
import com.yly.crm.dto.AccountLoginDTO;
import com.yly.crm.dto.LoginResultDTO;
import com.yly.crm.service.AccountService;
import com.yly.crm.service.impl.UserDetailsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.*;

/**
 * @author MoShuying
 * @date 2018/07/15
 */
@Slf4j
@Api(tags={"账户操作接口(登录)"})
@Validated
@RestController
@RequestMapping("/account")
public class AccountController {
  @Resource private AccountService accountService;
  @Resource private UserDetailsServiceImpl userDetailsService;
  @Resource private JwtUtil jwtUtil;

  @Operation(summary = "账户注册", description = "注册账户，签发token")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "2004", description = "账户名重复")
  })
  @PostMapping
  public Result register(
      @Parameter(required = true) @RequestBody @Valid final AccountDTO accountDTO,
      final BindingResult bindingResult) {
    // 账户持久化
    this.accountService.save(accountDTO);
    // 签发 token
    final UserDetails userDetails =
        this.userDetailsService.loadUserByUsername(accountDTO.getName());
    final String token = this.jwtUtil.sign(
            accountDTO.getName(),
            userDetails.getAuthorities(),
            accountService.getByNameWithRole(userDetails.getUsername()).getId());
    return ResultGenerator.genOkResult(token);
  }

  @Operation(summary = "账户登录", description = "账户登录，签发token")
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "OK"),
    @ApiResponse(responseCode = "1000", description = "密码错误")
  })
  @PostMapping("/token")
  public Result login(
      @Parameter(required = true) @RequestBody @Valid final AccountLoginDTO accountLoginDTO,
      final BindingResult bindingResult) {
    // {"name":"admin","password":"admin"}
    final String name = accountLoginDTO.getName();
    final String password = accountLoginDTO.getPassword();
    // 验证账户
    final UserDetails userDetails = this.userDetailsService.loadUserByUsername(name);
    if (!this.accountService.verifyPassword(password, userDetails.getPassword())) {
      return ResultGenerator.genFailedResult("密码错误");
    }
    // 更新登录时间
    this.accountService.updateLoginTimeByName(name);
    final String token = this.jwtUtil.sign(name, userDetails.getAuthorities(),accountService.getByNameWithRole(name).getId());
    // 返回Ant Design Admin提供的登录返回格式
    LoginResultDTO loginResultDTO = new LoginResultDTO();

    // 设置过期时间，和application-*.yml文件中的过期时间设定一致
    final long expireTime = this.jwtUtil.getJwtProperties().getExpireTime().toMillis();
    loginResultDTO.setExpireAt(new Date(new Date().getTime()+expireTime));
    loginResultDTO.setToken(token);
    loginResultDTO.setUserName(name);
    Map<String,Object> roles = new HashMap<String,Object>();
    roles.put("id",name);
    roles.put("operation",new String[]{"add","edit","delete"});

    loginResultDTO.getRoles().add(roles);
    loginResultDTO.setMessage("欢迎回来 "+name);
    return ResultGenerator.genOkResult(loginResultDTO);
  }

  @Operation(summary = "账户注销", description = "账户注销，使token失效")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
  @DeleteMapping("/token")
  public Result logout(@RequestHeader Map<String, String> headers) {
    String header = jwtUtil.getJwtProperties().getHeader();
    jwtUtil.invalidRedisToken(jwtUtil.getName(headers.get(header)).get());
    return ResultGenerator.genOkResult();
  }

  @PreAuthorize("#accountDTO.name == authentication.name or hasAuthority('ADMIN')")
  @Operation(summary = "更新账户", description = "更新账户信息")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
  @PatchMapping
  public Result update(@Parameter(required = true) @RequestBody final AccountDTO accountDTO) {
    this.accountService.updateByName(accountDTO);
    return ResultGenerator.genOkResult();
  }

  @PreAuthorize("hasAuthority('ADMIN')" +
          "or hasAuthority('主席')"+
          "or hasAuthority('高级主席')"+
          "or hasAuthority('副主席')"+
          "or hasAuthority('总裁')")
  @Operation(summary = "删除账户", description = "删除账户信息")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
  @Parameter(
      name = "id",
      description = "账户Id",
      required = true,
      in = ParameterIn.QUERY,
      example = "1")
  @DeleteMapping("/{id}")
  public Result delete(@PathVariable final Long id) {
    this.accountService.removeById(id);
    return ResultGenerator.genOkResult();
  }
//
//  @Operation(summary = "获取单个账户", description = "获取单个账户信息")
//  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
//  @Parameter(
//      name = "id",
//      description = "账户Id",
//      required = true,
//      in = ParameterIn.PATH,
//      example = "1")
//  @GetMapping("/{id}")
//  public Result detail(@PathVariable final Long id) {
//    final AccountWithRoleDO account = this.accountService.getByIdWithRole(id);
//    return ResultGenerator.genOkResult(account);
//  }
//
//  @Operation(summary = "获取账户列表", description = "获取多个账户信息")
//  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
//  @Parameters({
//    @Parameter(name = "page", description = "页号", in = ParameterIn.QUERY, example = "1"),
//    @Parameter(name = "size", description = "页大小", in = ParameterIn.QUERY, example = "10")
//  })
//  @Cacheable(value = "account.list", unless = "#result == null or #result.code != 200")
//  @CacheExpire(expire = 60)
//  @GetMapping
//  public Result list(
//      @RequestParam(defaultValue = "0") final Integer page,
//      @RequestParam(defaultValue = "0") final Integer size) {
//    AccountController.log.debug("==> No cache, find database");
//    PageHelper.startPage(page, size);
//    final List<AccountDO> list = this.accountService.listAll();
//    final PageInfo<AccountDO> pageInfo = PageInfo.of(list);
//    // 不显示 password 字段
//    final PageInfo<JSONObject> objectPageInfo = JsonUtils.deleteFields(pageInfo, PageInfo.class, "password");
//    return ResultGenerator.genOkResult(objectPageInfo);
//  }
}
