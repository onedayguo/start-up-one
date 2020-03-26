package interview;

import java.util.Scanner;

/**
 * @Description: 广发银行
 * @Auther: kami
 * @Date: 2020/3/26 14:48
 * @Version: 1.0.0
 */
public class GuangFaBank {
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//
//        while (in.hasNextLine()) {
//
//            }
//        }
    
    /**
     * @description: 求余数后三位
     * @return:
     * @auther: kami
     * @date: 2020/3/26 15:55
     */ 
    public static String tailOfPower (int a, int b) {
        // write code here
        if (a % 10 == 0) return "000";
        int sourceA = a % 1000;
        while (--b > 0){
            a = a % 1000;
            a *= sourceA;
        }
        int resInt = a % 1000;
        if (resInt < 10){
            return "00"+resInt;
        }else if (resInt < 100){
            return "0"+resInt;
        }
        return String.valueOf(resInt);
    }

    /**
     * @description: 中文数字转换\
     * “三十六亿九千二百万一千八百二十一” ***> "3692001821"
     * @return:
     * @auther: kami
     * @date: 2020/3/26 16:41
     */
    public String convert (String number) {
        // write code here
        return "0000";
    }


    public static void main(String[] args) {
        String msg = tailOfPower(1004,5);
        System.out.println(msg);
    }
}
