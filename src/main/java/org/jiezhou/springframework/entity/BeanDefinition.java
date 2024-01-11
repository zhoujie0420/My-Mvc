package org.jiezhou.springframework.entity;

import lombok.Data;

/**
 * 从配置中读取BeanDefinition
 *
 * @author: elk
 * @create: 2024-01-10 16:16
 **/
@Data

public class BeanDefinition {
    private Object bean;
    private Class beanClass;
    private String beanClassName;
    private Boolean IsSingleton;
    private PropertyValues propertyValues;

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            this.beanClass = Class.forName(beanClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        if (propertyValues == null) {
            propertyValues = new PropertyValues();
        }
        return propertyValues;
    }
}
