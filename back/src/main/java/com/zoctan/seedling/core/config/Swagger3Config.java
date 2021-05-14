package com.zoctan.seedling.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.service.*;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.zoctan.seedling.core.constant.ProjectConstant.SPRING_PROFILE_PRODUCTION;

/**
 * Swagger3
 *
 * @author Zoctan
 * @date 2020/11/06
 */
@PropertySource(
    value = "classpath:/META-INF/swagger3.yml",
    factory = YamlPropertySourceFactory.class)
@Configuration
public class Swagger3Config {

  @Value("${spring.profiles.active}")
  private String activeProfile;

  @Value("${application.name}")
  private String applicationName;

  @Value("${application.version}")
  private String applicationVersion;

  @Value("${application.description}")
  private String applicationDescription;

  @Value("${application.url.service}")
  private String applicationServiceUrl;

  @Value("${application.license}")
  private String applicationLicense;

  @Value("${application.url.license}")
  private String applicationLicenseUrl;

  @Value("${application.apis.selector}")
  private String selector;

  @Value("${author.name}")
  private String authorName;

  @Value("${author.url}")
  private String authorUrl;

  @Value("${author.email}")
  private String authorEmail;

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title(applicationName)
            .version(applicationVersion)
            .description(applicationDescription)
            .termsOfServiceUrl(applicationServiceUrl)
            .contact(new Contact(authorName, authorUrl, authorEmail))
            .license(applicationLicense)
            .licenseUrl(applicationLicenseUrl)
            .build();
  }

  @Bean
  public Docket docket() {
//添加head参数配置start
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.apiInfo())
        // 仅在非生产环境下生效
        .enable(!SPRING_PROFILE_PRODUCTION.equals(this.activeProfile))
        .select()
        .apis(RequestHandlerSelectors.basePackage(selector))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(securitySchemes())
        .securityContexts(securityContexts());
  }

  private List<SecurityScheme> securitySchemes(){
    return Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));
  }

  private List<SecurityContext> securityContexts() {
    return Collections.singletonList(
            SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .operationSelector(null)
                    .build()
    );
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    return Collections.singletonList(new SecurityReference("Authorization", new AuthorizationScope[]{authorizationScope}));
  }
}
