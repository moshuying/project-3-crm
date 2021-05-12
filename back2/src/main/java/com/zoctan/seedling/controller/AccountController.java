package com.zoctan.seedling.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.seedling.core.cache.CacheExpire;
import com.zoctan.seedling.core.jwt.JwtUtil;
import com.zoctan.seedling.core.response.Result;
import com.zoctan.seedling.core.response.ResultGenerator;
import com.zoctan.seedling.dto.AccountDTO;
import com.zoctan.seedling.dto.AccountLoginDTO;
import com.zoctan.seedling.entity.AccountDO;
import com.zoctan.seedling.entity.AccountWithRoleDO;
import com.zoctan.seedling.service.AccountService;
import com.zoctan.seedling.service.impl.UserDetailsServiceImpl;
import com.zoctan.seedling.util.JsonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/07/15
 */
@Slf4j
@Tag(name = "账户接口", description = "账户接口")
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
    final String token = this.jwtUtil.sign(accountDTO.getName(), userDetails.getAuthorities());
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
    final String token = this.jwtUtil.sign(name, userDetails.getAuthorities());
    return ResultGenerator.genOkResult(token);
  }

  @Operation(summary = "账户注销", description = "账户注销，使token失效")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
  @DeleteMapping("/token")
  public Result logout(@AuthenticationPrincipal final UsernamePasswordAuthenticationToken user) {
    this.jwtUtil.invalidRedisToken(user.getName());
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

  @PreAuthorize("hasAuthority('ADMIN')")
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
    this.accountService.deleteById(id);
    return ResultGenerator.genOkResult();
  }

  @Operation(summary = "获取单个账户", description = "获取单个账户信息")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
  @Parameter(
      name = "id",
      description = "账户Id",
      required = true,
      in = ParameterIn.PATH,
      example = "1")
  @GetMapping("/{id}")
  public Result detail(@PathVariable final Long id) {
    final AccountWithRoleDO account = this.accountService.getByIdWithRole(id);
    return ResultGenerator.genOkResult(account);
  }

  @Operation(summary = "获取账户列表", description = "获取多个账户信息")
  @ApiResponses({@ApiResponse(responseCode = "200", description = "OK")})
  @Parameters({
    @Parameter(name = "page", description = "页号", in = ParameterIn.QUERY, example = "1"),
    @Parameter(name = "size", description = "页大小", in = ParameterIn.QUERY, example = "10")
  })
  @Cacheable(value = "account.list", unless = "#result == null or #result.code != 200")
  @CacheExpire(expire = 60)
  @GetMapping
  public Result list(
      @RequestParam(defaultValue = "0") final Integer page,
      @RequestParam(defaultValue = "0") final Integer size) {
    AccountController.log.debug("==> No cache, find database");
    PageHelper.startPage(page, size);
    final List<AccountDO> list = this.accountService.listAll();
    final PageInfo<AccountDO> pageInfo = PageInfo.of(list);
    // 不显示 password 字段
    final PageInfo<JSONObject> objectPageInfo =
        JsonUtils.deleteFields(pageInfo, PageInfo.class, "password");
    return ResultGenerator.genOkResult(objectPageInfo);
  }
}
