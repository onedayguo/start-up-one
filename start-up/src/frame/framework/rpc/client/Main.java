package frame.framework.rpc.client;

import frame.framework.rpc.server.HelloService;

import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        HelloService service = Client.getRemoteProxyObj(Class.forName("frame.framework.rpc.server.HelloService"),new InetSocketAddress("127.0.0.1",9999));
        System.out.println(service.sayHi("kami is coming"));
    }
}
