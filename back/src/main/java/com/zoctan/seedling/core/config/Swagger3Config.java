package com.zoctan.seedling.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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

  @Bean
  public Docket docket() {

    return new Docket(DocumentationType.OAS_30)
        .apiInfo(this.apiInfo())
        // 仅在非生产环境下生效
        .enable(!SPRING_PROFILE_PRODUCTION.equals(this.activeProfile))
        .select()
        .apis(RequestHandlerSelectors.basePackage(selector))
        .paths(PathSelectors.any())
        .build();
  }

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
}
