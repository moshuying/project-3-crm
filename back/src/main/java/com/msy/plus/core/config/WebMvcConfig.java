package com.msy.plus.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring MVC 配置
 *
 * @author MoShuying
 * @date 2018/05/27
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
  /** 使用阿里 FastJson 作为 JSON MessageConverter */
  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    final FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
    final FastJsonConfig config = new FastJsonConfig();
    // 支持的输出类型
    final List<MediaType> supportedMediaTypes = new ArrayList<>();
    supportedMediaTypes.add(MediaType.APPLICATION_JSON);
    supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
    supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
    supportedMediaTypes.add(MediaType.TEXT_HTML);
    converter.setSupportedMediaTypes(supportedMediaTypes);
    config.setSerializerFeatures(
        // 保留空的字段
        // SerializerFeature.WriteMapNullValue,
        // Number null -> 0
        SerializerFeature.WriteNullNumberAsZero,
        // 美化输出
        SerializerFeature.PrettyFormat);
    converter.setFastJsonConfig(config);
    converter.setDefaultCharset(StandardCharsets.UTF_8);
    converters.add(converter);
  }

  /** 资源控制器 */
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry
          .addResourceHandler("/webjars/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    if (!registry.hasMappingForPattern("/swagger-ui/**")) {
      // It is recommended by Springfox 3.x to disable caching of the static Swagger page content
      registry
          .addResourceHandler("/swagger-ui/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
          .resourceChain(false);
    }
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/swagger-ui.html", "/swagger-ui/index.html");
    registry.addRedirectViewController("/swagger-ui", "/swagger-ui/index.html");
    registry.addRedirectViewController("/swagger-ui/", "/swagger-ui/index.html");
  }
}
