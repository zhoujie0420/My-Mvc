package org.jiezhou.springframework.context;

import java.util.Objects;

/**
 * 应用程序上下文接口
 *
 * @author: elk
 * @create: 2024-01-10 16:12
 **/

public interface ApplicationContext {

    Object getBean(Class clazz) throws Exception;

    Object getBean(String beanName) throws Exception;
}
