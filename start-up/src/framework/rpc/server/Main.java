package framework.rpc.server;

public class Main {
    public static void main(String[] args) throws Exception {
        //服务中心，端口9999
        Server server = new ServerCenter(9999);
        //将接口以及实现类注册到服务中心
        server.register(HelloService.class,ServiceImpl.class);
        server.start();
    }
}
