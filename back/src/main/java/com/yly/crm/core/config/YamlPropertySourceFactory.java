package com.yly.crm.core.config;

import com.yly.crm.core.exception.YamlNotFoundException;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Optional;
import java.util.Properties;

/**
 * Yml配置文件工厂
 *
 * @author MoShuying
 * @date 2020/11/12
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

  @Override
  public PropertySource<?> createPropertySource(final String name, final EncodedResource resource) {
    final YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
    factory.setResources(resource.getResource());
    factory.afterPropertiesSet();
    final Properties ymlProperties =
        Optional.ofNullable(factory.getObject()).orElseThrow(YamlNotFoundException::new);
    final String propertyName =
        Optional.ofNullable(name).orElse(resource.getResource().getFilename());
    Optional.ofNullable(propertyName).orElseThrow(YamlNotFoundException::new);
    return new PropertiesPropertySource(propertyName, ymlProperties);
  }
}
