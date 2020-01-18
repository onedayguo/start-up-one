package advance.netty;

/**
 * @Description: TODO
 * @Auther: kami
 * @Date: 2020/1/18 14:59
 * @Version: 1.0.0
 */

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @description: 客户端
 * @auther: kami
 * @date: 2020/1/18 15:00
 */
public class IoClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}