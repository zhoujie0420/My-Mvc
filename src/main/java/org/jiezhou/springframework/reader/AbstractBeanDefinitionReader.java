package org.jiezhou.springframework.reader;

import org.jiezhou.springframework.entity.BeanDefinition;
import org.jiezhou.springframework.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: elk
 * @create: 2024-01-10 16:57
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

}
