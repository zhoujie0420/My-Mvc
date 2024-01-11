package org.jiezhou.springframework.reader;

/**
 * bean 定义读取
 *
 * @author: elk
 * @create: 2024-01-10 16:57
 **/

public interface BeanDefinitionReader {

    void loadBeanDefinitions(String location) throws Exception;
}
