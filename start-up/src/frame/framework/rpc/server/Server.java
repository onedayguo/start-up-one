package frame.framework.rpc.server;

import java.io.IOException;

public interface Server {
     void start() throws IOException, Exception;
     void stop();
    //注册服务
    void register(Class service,Class serviceImpl);
}
