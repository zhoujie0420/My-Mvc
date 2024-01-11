package org.jiezhou.springframework.context;

import org.jiezhou.springframework.factory.BeanFactory;

import java.util.Objects;

/**
 * @author: elk
 * @create: 2024-01-10 16:14
 **/

public abstract class AbstractApplicationContext implements ApplicationContext {

    BeanFactory beanFactory;
    @Override
    public Object getBean(Class clazz) throws Exception {
        return beanFactory.getBean(clazz);
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return beanFactory.getBean(beanName);
    }
}
