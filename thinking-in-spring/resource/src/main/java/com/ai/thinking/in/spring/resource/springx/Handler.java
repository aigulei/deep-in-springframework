package com.ai.thinking.in.spring.resource.springx;


import org.springframework.util.StreamUtils;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class Handler extends sun.net.www.protocol.x.Handler {
    //-Djava.protocol.handler.pkgs=com.ai.thinking.in.spring.resource
    public static void main(String[] args) throws Exception {
        URL url = new URL("springx:///META-INF/product.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
