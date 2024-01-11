package org.jiezhou.springframework.entity;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 键值对，表示注入对象的属性
 * @author: elk
 * @create: 2024-01-10 16:22
 **/
@Data
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValueList.add(propertyValue);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }

}
