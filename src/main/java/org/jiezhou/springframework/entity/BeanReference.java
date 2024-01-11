package org.jiezhou.springframework.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: elk
 * @create: 2024-01-10 16:26
 **/
@Getter
public class BeanReference {
    private String name;
    private Object bean;
    public BeanReference(String name) {
        this.name = name;
    }




}
