package org.jiezhou.springframework.entity;

import lombok.Data;

/**
 * @author: elk
 * @create: 2024-01-10 16:23
 **/
@Data
public class PropertyValue {

    private final  String name;
    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

}
