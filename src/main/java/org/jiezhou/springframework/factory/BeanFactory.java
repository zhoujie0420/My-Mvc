package org.jiezhou.springframework.factory;

import org.jiezhou.springframework.entity.BeanDefinition;

/**
 * bean 工厂接口
 *
 * @author: elk
 * @create: 2024-01-10 16:15
 **/

public interface BeanFactory {
    Object getBean(String beanName) throws Exception;

    Object getBean(Class clazz) throws Exception;

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception;
}
