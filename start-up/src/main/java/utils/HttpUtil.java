package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Auther: kami
 * @Date: 2019/9/20 23:41
 * @Description:
 */
public class HttpUtil {

    public static void main(String[] args) {
        httpURLConectionGET();
    }
    public static final String GET_URL = "http://sif-iec.ecnu.edu.cn/4698/list.html";
    public static final int COUNT = 1000000000;
    //get 请求
    public  static void httpURLConectionGET() {
        try {
            URL url = new URL(GET_URL);// 字符串转成请求地址
            HttpURLConnection connection = null;
            for (int i = 0; i < COUNT; i++) {
                connection = (HttpURLConnection) url.openConnection();// 打开连接
                connection.connect();// 连接会话
                System.out.println("第"+i+"次连接成功");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 响应结果为输入流
            String line;
            StringBuilder sb = new StringBuilder();// 输出的结果
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            connection.disconnect();// 断开连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求失败 :" + e.getMessage());
        }
    }
}
