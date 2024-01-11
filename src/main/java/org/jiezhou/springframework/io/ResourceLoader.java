package org.jiezhou.springframework.io;

import java.net.URL;

/**
 * @author: elk
 * @create: 2024-01-10 17:03
 **/

public class ResourceLoader {
    public Resource getResource(String location) {
        URL url = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(url);
    }
}
