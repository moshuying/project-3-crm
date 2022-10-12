package com.yly.crm.core.config;

import com.yly.crm.filter.AuthenticationFilter;
import com.yly.crm.filter.MyAuthenticationEntryPoint;
import com.yly.crm.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * 安全设置
 *
 * @author MoShuying
 * @date 2018/05/27
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private static final String[] ANONYMOUS_LIST = {
    "/druid/**",
    "/swagger-ui/",
    "/swagger-ui/index.html",
    "/swagger-ui.html",
    "/swagger-ui/**",
    "/v2/api-docs",
    "/v3/api-docs",
    "/swagger-resources",
    "/swagger-resources/**",
    "/webjars/**",
    "/bizs/**"
  };

  @Resource private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
  @Resource private AuthenticationFilter authenticationFilter;

  /** 使用随机加盐哈希算法对密码进行加密 */
  @Bean
  public static PasswordEncoder passwordEncoder() {
    // 默认强度10，可以指定 4 到 31 之间的强度
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public UserDetailsServiceImpl userDetailsService() {
    return new UserDetailsServiceImpl();
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth
        // 自定义获取账户信息
        .userDetailsService(this.userDetailsService())
        // 设置密码加密
        .passwordEncoder(WebSecurityConfig.passwordEncoder());
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
        // 禁用页面缓存
        .headers()
        .cacheControl()
        .and()
        .and()
        // 关闭 cors 验证
        .cors()
        .disable()
        // 关闭 csrf 验证
        .csrf()
        .disable()
        // 无状态 session
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // 异常处理
        .exceptionHandling()
        // 因为 RESTFul 没有登录界面所以只显示未登录 Json
        .authenticationEntryPoint(this.myAuthenticationEntryPoint)
        .and()
        // 身份过滤器
        .addFilterBefore(this.authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        // 认证访问
        .authorizeRequests()
        // 允许匿名请求
        .antMatchers(ANONYMOUS_LIST)
        .permitAll()
        // 注册和登录
        .antMatchers(HttpMethod.POST, "/account", "/account/token")
        .permitAll()
        // 预请求
        .antMatchers(HttpMethod.OPTIONS)
        .permitAll()
        // 对除上面特别请求外所有都鉴权认证
        .anyRequest()
        .authenticated();
  }
}
