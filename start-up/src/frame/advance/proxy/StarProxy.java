package frame.advance.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StarProxy implements InvocationHandler {

    //目标类，也就是代理对象
    private Object target;
    public void setTarget(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //这里可以做增强
        System.out.println("经纪人收钱");
        Object result = method.invoke(target,args);//
        return result;
    }
    //生成代理类
    public Object createProxyObj(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
}
