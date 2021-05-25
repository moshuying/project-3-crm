package com.msy.plus.filter;

import com.msy.plus.util.IpUtils;
import com.msy.plus.util.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.msy.plus.core.constant.ProjectConstant.SPRING_PROFILE_DEVELOPMENT;
import static com.msy.plus.core.constant.ProjectConstant.SPRING_PROFILE_PRODUCTION;

/**
 * 跨域过滤器
 *
 * @author MoShuying
 * @date 2018/06/04
 */
@Slf4j
@Order(1)
@Component
public class CorsFilter implements Filter {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Override
    public void init(final FilterConfig filterConfig) {
        log.debug("==> CorsFilter init");
    }

    @Override
    public void doFilter(
            final ServletRequest servletRequest,
            final ServletResponse servletResponse,
            final FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 仅在生产环境下生效
        if (SPRING_PROFILE_PRODUCTION.equals(this.activeProfile)) {
            // 设置允许多个域名请求
            String[] allowDomains = {"http://project.crm3.msy.plus","https://project.crm3.msy.plus"};
            Set<String> allowOrigins = new HashSet(Arrays.asList(allowDomains));
            String originHeads = request.getHeader("Origin");
            if(allowOrigins.contains(originHeads)){
                //设置允许跨域的配置
                // 这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
                response.setHeader("Access-Control-Allow-Origin", originHeads);
            }
        }
        if(SPRING_PROFILE_DEVELOPMENT.equals(this.activeProfile)){
            // 允许所有来源
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader(
                "Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization");
        // 明确允许通过的方法，不建议使用 *
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Expose-Headers", "*");

        // 预请求后，直接返回
        // 返回码必须为 200 否则视为请求失败
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return;
        }

        log.debug(
                "==> IP<{}> Request: [{}] {}",
                IpUtils.getIpAddress(),
                request.getMethod(),
                UrlUtils.getMappingUrl(request));

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.debug("==> CorsFilter destroy");
    }
}
