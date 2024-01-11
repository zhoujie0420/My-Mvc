package org.jiezhou.springframework.annotation;

import java.lang.annotation.*;

/**
 * @author: elk
 * @create: 2024-01-10 16:04
 **/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {

}
