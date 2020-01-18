package framework.rpc.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Java读取并下载网络文件
 * @author kami
 * @createTime 2019年6月17号 14.29
 * @updateTime
 * @Email:zh_guomail@163.com
 * @version:1.0.0
 * @referenceLink
 *  <a href="http://blog.csdn.net/xb12369/article/details/40543649/"> java 从网络Url中下载文件</a>
 */
public class DownloadFromNetwork {
    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        Date start = new Date();
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }

        System.out.println("info:"+url+" download success");
        Date end = new Date();
        long time = (end.getTime() - start.getTime()) / 1000;
        System.out.println("time interval is:"+time);
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }


    public static void main(String[] args) {
        try{
            //for (int i = 201; i <=520; i++) {

                downLoadFromUrl("http://cdn.hdpornpictures.com/2017-01-06/394549_10.jpg",".jpg","E:/Downloads/wallpaper/baiduSkin");

            //}
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}