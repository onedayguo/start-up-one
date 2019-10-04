package framework.rpc.http;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
public class Downloader {
    private Queue <String> col;//用队列，更加真实
    private String base,path;
    public Downloader (String abase,String apath) {
        base=abase;
        path=apath;
        col  = new LinkedList<>();//不能直接构造queue对象，用LinkedList作为其实现类，优先队列也可
    }
    /*
     * @return html代码
     * 获取网页html，这里默认utf8编码
     */
    public String getHtml() throws Exception {
        URL url = new URL(base);
        HttpURLConnection connection= (HttpURLConnection)url.openConnection();
        String res="";
        Scanner cin = new Scanner (connection.getInputStream());
        while (cin.hasNextLine())//一行一行读入
            res += cin.nextLine()+'\n';
        cin.close();
        return res;
    }
    /*
     * 辅助函数，从html中筛出图片，放进队列中,更好的方法是使用jsoup，等学习了再用
     */
    public void filter () throws Exception {
        String res = this.getHtml();
        String homepage = base;
        int pos0= base.indexOf("//");
        int pos1 = base.indexOf('/',pos0+2);
        if (pos1!=-1)
            homepage = base.substring(0,pos1);
        Pattern p = Pattern.compile("<img.*src=\"(.*?)\"");//正则表达式捕获图片url
        Matcher m = p.matcher(res);
        while (m.find()){//每找到一次图片就放进队列中
            String tmp = m.group(1);
            if (tmp.indexOf('.')!=-1) //是图片一定得有dot
                if (tmp.substring(0,2).equals("//"))
                    col.add("http:"+tmp);
                else if (tmp.charAt(0)=='/')
                    col.add(homepage+tmp);
                else
                    col.add(tmp);
        }
    }

    public Queue<String> getCol() {
        return col;
    }
    /*
     * 下载图片至指定路径，这里用的是最朴素的方法，可以用花哨点的imageIO，一会再研究
     * 如果文件同名会自动覆盖，需修正
     */
    public void download()throws Exception {
        filter();
        File fp = new File(path);
        if (!fp.exists())
            fp.mkdirs();//如果该路径不存在，创建它
        int i=0;
        InputStream cin = null;
        OutputStream cout = null;
        while (col.size()!=0) {//遍历整个队列
            String tmp = col.remove();
            String filename = tmp.substring(tmp.lastIndexOf('/'));//找出文件名
            URL url = new URL(tmp);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            cin = connection.getInputStream();
            File file =new File (fp+filename);//这里默认fp最后不带/，文件名前面带/
            cout = new FileOutputStream (file);//输出流
            while ((i=cin.read())!=-1) //将其当作普通二进制文件进行处理，read到头时返回-1
                cout.write(i);//写入文件
            cin.close();//关闭输入输出流
            cout.close();
        }
    }


    public static void main(String[] args) {
        Downloader downloader = new Downloader("http://www.hdpornpictures.com","E:\\Downloads\\wallpaper\\baiduSkin");
        try {
            downloader.download();
            System.out.println("下载成功！");
        }catch (FileNotFoundException e) {//关于异常的代码写得真的很偷懒，下次补上
            e.printStackTrace();
            System.out.println("小错误，不慌");
        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("下载失败");
        }



    }
}

