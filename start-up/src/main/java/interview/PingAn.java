package interview;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Auther: kami
 * @Date: 2019/10/10 20:40
 * @Description: 平安科技软件工程师第一轮笔试
 */
public class PingAn {


    //region 编程题1.最长公共字符串
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()){
//            String str1 = in.next();
//            String str2 = in.next();
//            longestSubString(str1,str2);
//        }
//        //longestSubString("abc123dfer","dhgydgsb123");
//    }
//    public static void longestSubString(String str1,String str2){
//        ArrayList<Integer> indexs = new ArrayList<>();
//        int windowSize = 0;
//        int n1 = str1.length();
//        int n2 = str2.length();
//        for (int i = 0; i < n1; i++) {
//            int tmpWindow = 0;
//            for (int j = 0; j < n2; j++) {
//                int tempI = i;
//                while (tempI < n1 &&j < n2 && str1.charAt(tempI++) == str2.charAt(j++)){
//                    tmpWindow++;
//                }
//                if (tmpWindow > windowSize) {
//                    indexs.clear();
//                    windowSize = tmpWindow;
//                    indexs.add(i);
//                } else if (tmpWindow != 0 && tmpWindow == windowSize) {
//                    indexs.add(i);
//                }
//                tmpWindow = 0;
//            }
//        }
//
//        for (Integer index:indexs) {
//            System.out.println(str1.substring(index,index+windowSize));
//        }
//    }

    //endregion


    //region 编程题2.给定2个IP地址，和子网掩码，判断是否是同一子网下
    /**
     思路：1.拆分字符串，按照 . 拆分
           2.将字符串数组转化为
     **/

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNext()){
//            String ip1 = in.next();
//            String  ip2 = in.next();
//            int subNetCode = in.nextInt();
//
//            System.out.println(inLocalNet(ip1,ip2,subNetCode));
//
//        }
        System.out.println("hello,world");

    }

    public static boolean inLocalNet(String ip1,String ip2,int code){
        String[] subIp1 = ip1.split(".");
        String[] subIp2 = ip2.split(".");
        StringBuilder binaryIp1 = new StringBuilder();
        StringBuilder binaryIp2 = new StringBuilder();
        for (String item:subIp1) {
            binaryIp1.append( Integer.toBinaryString(Integer.valueOf(item)));
        }
        for (String item:subIp2){
            binaryIp2.append(Integer.toBinaryString(Integer.valueOf(item)));
        }
        return binaryIp1.subSequence(0,code+1).equals(binaryIp2.subSequence(0,code+1));

    }


    //endregion



}
