package framework.serverlet;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpsServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Description: TODO
 * @Auther: kami
 * @Date: 2020/4/16 22:14
 * @Version: 1.0.0
 */
public class MyServlet extends HttpServerProvider {
    @Override
    public HttpServer createHttpServer(InetSocketAddress addr, int backlog) throws IOException {
        return null;
    }

    @Override
    public HttpsServer createHttpsServer(InetSocketAddress addr, int backlog) throws IOException {
        return null;
    }
}
