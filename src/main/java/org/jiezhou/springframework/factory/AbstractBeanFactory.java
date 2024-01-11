package org.jiezhou.springframework.factory;

import org.jiezhou.springframework.entity.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: elk
 * @create: 2024-01-10 16:29
 **/

public abstract class AbstractBeanFactory implements BeanFactory {
    ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();


    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + beanName + " is defined");
        }
        if (!beanDefinition.getIsSingleton() && beanDefinition.getBean() != null) {
            return beanDefinition.getBean();
        } else {
            return doCreateBean(beanDefinition);
        }
    }

    @Override
    public Object getBean(Class clazz) throws Exception {
        BeanDefinition beanDefinition = null;
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (beanClass == clazz || beanClass.isAssignableFrom(clazz)) {
                beanDefinition = entry.getValue();
            }
        }
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + clazz + " is defined");
        }
        if (!beanDefinition.getIsSingleton() && beanDefinition.getBean() != null) {
            return beanDefinition.getBean();
        } else {
            return beanDefinition.getBean();
        }
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;

    public void populateBeans() throws Exception {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            doCreateBean(entry.getValue());
        }
    }
}
