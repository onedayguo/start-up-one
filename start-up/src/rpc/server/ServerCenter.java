package rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 代理注册中心，在客户端和服务端之间
 * 代理对象，中间者
 */

public class ServerCenter implements Server{

    //map：服务端的所有可提供客户端访问的接口，都注册到该map中
    //key 接口的名字“HelloService”,value 真正的helloService实现
    private static HashMap<String,Class> serviceRegiseter = new HashMap<>();
    private static int port;
    //连接池中存在多个连接对象，每个连接对象都可以处理一个客户请求
    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static boolean isRunning = false;//
    public ServerCenter(int port){
        this.port = port;
    }
    //开启服务
    @Override
    public void start() throws IOException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(9999));
        isRunning = true;//服务启动
        while (true){
            //具体服务内容，接收客户端请求，处理请求，并返回结果

                System.out.println("start server.........");
                Socket socket = server.accept();//accept 接收客户端的消息，消息用socket包装
                executor.execute(new ServiceTask( socket));//传递socket，启动线程处理



            }

        }



    @Override
    public void stop() {//关闭服务
        isRunning = false;
        executor.shutdown();
    }

    @Override
    public void register(Class service,Class serviceImpl) {
        serviceRegiseter.put(service.getName(),serviceImpl);
    }

    private static class ServiceTask implements Runnable{

        private Socket socket;
        public ServiceTask(){
        }
        public ServiceTask(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            ObjectInputStream input = null;
            ObjectOutputStream out = null;
            //处理多个请求，多线程，使用线程池
            //接收到客户单连接即请求，处理该请求
            try {
                input = new ObjectInputStream(socket.getInputStream());
                //因为objectInputStream对发送数据的顺序严格要求，一次需要参照发送的顺序逐个接收处理
                String serviceName = input.readUTF();//获取接口名
                String methodName = input.readUTF();//获取方法名
                Class[] parameterTypes = (Class[]) input.readObject();//获取方法的参数类型
                Object[] arguments =(Object[]) input.readObject();//获取参数列表
                //根据客户请求，到map中找到阈值对应的具体接口
                Class ServiceClass = serviceRegiseter.get(serviceName);
                Method method = ServiceClass.getMethod(methodName,parameterTypes);
                //执行该方法，反射，得到返回值
                Object result =  method.invoke(ServiceClass.getDeclaredConstructor().newInstance(),arguments);//1.实现接口的对象 2.参数列表

                //客户端将方法执行的返回值，传给客户端，输出流//
                out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(result);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (input != null) {
                        input.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }

        }
    }

}
}