package advance.internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Auther: kami
 * @Date: 2019/10/4 18:15
 * @Description:
 */
public class InternetAddress {
    public static void main(String[] args) throws UnknownHostException {
        // static InetAddress getLocalHost​() 获取本机的IP地址
        InetAddress ip1 = InetAddress.getLocalHost();
        // System.out.println("ip1 = " + ip1); // DESKTOP-6MABD4P/192.168.99.65

        // String getHostName​() 获取主机名
        System.out.println("HostName: " + ip1.getHostName());
        // String getHostAddress​() 获取IP地址字符串
        System.out.println("HostAddress: " + ip1.getHostAddress());

        // static InetAddress getByName​(String host) 通过ip地址字符串,返回IP地址对象
        InetAddress ip2 = InetAddress.getByName("www.baidu.com");// 参数可以给IP地址/网址
        System.out.println("ip2 = " + ip2); // /192.168.99.46

    }

}
