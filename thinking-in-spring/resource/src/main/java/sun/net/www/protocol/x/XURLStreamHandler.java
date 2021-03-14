package sun.net.www.protocol.x;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class XURLStreamHandler implements URLStreamHandlerFactory {
    @Override
    public URLStreamHandler createURLStreamHandler(String protocol) {
        if("x".equals(protocol)){
            return new Handler();
        }
        return new Handler();
    }
}
