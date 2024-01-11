package org.jiezhou.springframework.factory;

import org.jiezhou.springframework.entity.BeanDefinition;
import org.jiezhou.springframework.entity.BeanReference;
import org.jiezhou.springframework.entity.PropertyValue;

import java.lang.reflect.Field;

/**
 * @author: elk
 * @create: 2024-01-10 16:36
 **/

public class AutowiredCapableBeanFactory extends AbstractBeanFactory {
    @Override
    Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition.getIsSingleton() && beanDefinition.getBean() != null) {
            return beanDefinition.getBean();
        }
        Object bean = beanDefinition.getBeanClass().newInstance();
        if (beanDefinition.getIsSingleton()) {
            beanDefinition.setBean(bean);
        }
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 为新创建的bean 注入属性
     */
    void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {
        for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) propertyValue.getValue();
                // 优先按照自定义名称匹配
                BeanDefinition refDefinition = beanDefinitionMap.get(beanReference.getName());
                if (refDefinition != null) {
                    if (!refDefinition.getIsSingleton() || refDefinition.getBean() == null) {
                        value = doCreateBean(refDefinition);
                    } else {
                        value = refDefinition.getBean();
                    }
                }
            }

            if (value == null) {
                throw new RuntimeException("无法注入");
            }
            field.setAccessible(true);
            field.set(bean, value);
        }
    }
}
