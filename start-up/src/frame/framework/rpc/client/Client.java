package frame.framework.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
    //获取代表服务端接口的动态代理对象
    @SuppressWarnings("unchecked")
    public static <T> T getRemoteProxyObj(Class serviceInterface, InetSocketAddress address){
        //newProxyInstance(a,b,c)
        /*
        a:类加载器：需要代理那个类（例如HelloService那个类），就需要将HelloService的类加载器 传入第一个额参数
        b:需要代理的对象，具备哪些方法----接口
        c:单继承，多实现，
         */
        return (T) Proxy.newProxyInstance(serviceInterface.getClassLoader(), new Class<?>[]{serviceInterface}, new InvocationHandler() {
            //proxy代理的对象， method调用的方法  args 参数列表
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ObjectOutputStream output = null;
                ObjectInputStream input = null;
                try {
                    //客户端向服务端发送请求，请求某一个接口的方法
                    Socket socket = new Socket();
                    //socketAddress ip + 端口
                    socket.connect(address);
                    //发送，序列化流
                    output = new ObjectOutputStream(socket.getOutputStream());
                    //接口名，方法名，参数类型，参数列表
                    output.writeUTF(serviceInterface.getName());//发送接口名
                    output.writeUTF(serviceInterface.getName());//发送方法名
                    output.writeObject(method.getParameterTypes());//发送参数类型
                    output.writeObject(args);//发送参数列表
                    //等待服务端处理....
                    //接收服务端处理的返回值
                     input = new ObjectInputStream(socket.getInputStream());
                    return input.readObject();//客户端-服务端-返回值
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    return null;
                } finally {
                    try {
                        if (output != null) {
                            output.close();
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
        });//动态代理对象
    }
}
