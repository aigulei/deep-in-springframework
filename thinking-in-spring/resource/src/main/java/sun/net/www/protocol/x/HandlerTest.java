package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;


import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

public class HandlerTest {
    public static void main(String[] args) throws Exception {
        URL url = new URL("x:///META-INF/default.properties");
        url.setURLStreamHandlerFactory(new XURLStreamHandler());
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
