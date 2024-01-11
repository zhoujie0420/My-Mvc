package org.jiezhou.springframework.io;

import java.io.InputStream;

/**
 * @author: elk
 * @create: 2024-01-10 17:04
 **/

public interface Resource {

    InputStream getInputStream() throws Exception;
}
