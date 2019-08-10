package rpc.http;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CatchImage {


    // 地址
    private static final String URL = "http://news.163.com/";
    // 编码
    private static final String ECODING = "UTF-8";
    // 获取img标签正则
    private static final String IMGURL_REG = "<img src=(.*?)[^>]*?>";
    // 获取src路径的正则
    private static final String IMGSRC_REG = "http:.+(\\.jpeg|\\.jpg|\\.png)\"";


    public static void main(String[] args) throws Exception {
        CatchImage cm = new CatchImage();
// 获得html文本内容
        String HTML = cm.getHTML(URL);

// 获取图片标签
        List<String> imgUrl = cm.getImageUrl(HTML);

// 获取图片src地址
        List<String> imgSrc = cm.getImageSrc(imgUrl);

// 下载图片 cm.Download(imgSrc);
        cm.Download(imgSrc);
    }

    /**
     * 
     * 
     * 获取HTML内容
     * 
     * @param url
     * @return
     * @throws Exception
     **/
    private String getHTML(String url) throws Exception {
        URL uri = new URL(url);
        URLConnection connection = uri.openConnection();
        InputStream in = connection.getInputStream();
        byte[] buf = new byte[1024];
        int length = 0;
        StringBuffer sb = new StringBuffer();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            sb.append(new String(buf, ECODING));
        }
        in.close();
        return sb.toString();
    }


    /**
     * 获取ImageUrl地址
     * 
     * @param HTML
     * 
     * @return
     */
    private List<String> getImageUrl(String HTML) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(HTML);
        List<String> listImgUrl = new ArrayList<String>();
        while (matcher.find()) {
            listImgUrl.add(matcher.group());
        }
// for(String str: listImgUrl){
// System.out.println(str);
// }
        return listImgUrl;
    }


    /**
     * 获取ImageSrc地址
     * 
     * @param listImageUrl
     * 
     * @return
     **/
    private List<String> getImageSrc(List<String> listImageUrl) {
        List<String> listImgSrc = new ArrayList<String>();
        for (String image : listImageUrl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                listImgSrc.add(matcher.group().substring(0,
                        matcher.group().length() - 1));


            }
        }
// for (String image : listImgSrc) {
// System.out.println(image);
// }
        return listImgSrc;
    }


    /**
     * 下载图片
     * 
     * @param listImgSrc
     **/
    private void Download(List<String> listImgSrc) {
        int count = 0;
        ArrayList al = new ArrayList();
        for (String urll : listImgSrc) {
            System.out.println(urll);
            Pattern p = Pattern.compile("\\.jpg|png|jpeg[^_]");
            Matcher m = p.matcher(urll);
            while (m.find()) {


                al.add(m.group());
            }
        }
        for (Object s : al) {
            System.out.println(s);
        }


        try {
            for (String url : listImgSrc) {
                System.out.println(url);
                URL uri = new URL(url);
                InputStream in = uri.openStream();


                FileOutputStream fo = new FileOutputStream("E:/Downloads/wallpaper/baiduSkin" + count
                        + al.get(count));


                byte[] buf = new byte[1024];
                int length = 0;
                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println("下载完成");
                count++;
            }
        } catch (Exception e) {
            System.out.println("下载失败");
        }
        System.out.println(count);
    }
}

