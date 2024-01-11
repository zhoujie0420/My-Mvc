package org.jiezhou.springframework.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: elk
 * @create: 2024-01-10 17:04
 **/

public class UrlResource implements Resource {
    private URL url;

    public UrlResource(URL url) {
        this.url = url;
    }

    public InputStream getInputStream() throws Exception {
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }
}
