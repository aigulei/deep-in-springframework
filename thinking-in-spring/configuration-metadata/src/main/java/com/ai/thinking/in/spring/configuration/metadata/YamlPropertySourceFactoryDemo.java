package com.ai.thinking.in.spring.configuration.metadata;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * {@link org.springframework.core.io.support.PropertySourceFactory}
 */
public class YamlPropertySourceFactoryDemo implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(resource.getResource());
        Properties yamlProperties = bean.getObject();

        return new PropertiesPropertySource(name,yamlProperties);
    }
}
