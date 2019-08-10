package reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestMain {

    public static void main(String[] args){
        //通过一个Class队形来操作Person.class类
        try {

             Class persion = Class.forName("reflect.Person");
             Object o = persion.getConstructor().newInstance();
             Method s = persion.getMethod("toString");
             System.out.println(s.invoke(o));
            //类自己有结构  权限修饰符 特征修饰符  类名字 继承  实现
//            int modifiers = persion.getModifiers();//每一个修饰符，用一个整数来表示，0,1,2,4,8,16,32,64,256,512
//            //0默认不写，1-public，2--private， 4--protecd, 4-static,16-final,32-synchronized...
//
//            ArrayList<String> list = new ArrayList<>();
//            //获取集合对应的那个类
//            Class c = ArrayList.class;
//            c.getSuperclass();

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
