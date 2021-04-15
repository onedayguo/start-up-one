package interview;

import java.util.Scanner;

/**
 * @Description: 字节跳动
 * @Author: kami
 * @Date: 2020/6/21 13:28
 * @Version: 1.0.0
 */
public class ByteDance {
/*
    public static void main(String[] args) {
        //region 3.0
//        Scanner in = new Scanner(System.in);
//        System.out.println("输入正整数N值：");
//        int N = in.nextInt();
//        int[] year = new int[N];
//        for(int i = 0; i < N; i++){
//            year[i] = in.nextInt();
//        }
//        int sum = 0;
//        int[] money = new int[N];
//        for (int i = 0; i < N-1; i++) {
//            if (year[i] >= year[i+1]){
//                int start = i;
//                while (year[start] >= year[start+1] && start+1 < N){
//                    start++;
//                }
//                money[start] = 100;
//                for (int j = start; j > i; j--) {
//                    if (year[j-1] > year[j]) money[j-1] = money[j]+100;
//                    if (year[j-1] == year[i]) money[j-1] = money[j];
//                }
//            }
//            if (year[i] < year[i+1]) {
//                if (i == 0) year[i] = 100;
//                else year[i] = money[i-1] + 100;
//            }
//        }
//        for (int i = 0; i < N; i++) {
//            sum += money[i];
//        }
//        System.out.println(sum);
        //endregion


         //2.0
        Scanner in = new Scanner(System.in);
        System.out.println("输入N和K值：");
        int N,K;
        for(int i = 0; i < 1; i++){
            N = in.nextInt();//长度N
            K = in.nextInt();//写下K次
        }
        System.out.println("请输入二进制字符串，长度为 N+K-1：");
        String binaryStr = in.next();//长度 N+K-1

        //region 1.0
//        Scanner in = new Scanner(System.in);
//        System.out.println("输入N值：");
//        int n = in.nextInt();
//        System.out.println("输入时间值：");
//        int[][] value = new int[n+1][2];
//        for(int i = 0; i < n; i++){
//            value[i][0] = in.nextInt();// 0<= value <24
//            value[i][1] = in.nextInt();//0<= value <60
//        }
//        int X = in.nextInt(); //到教室需要时间  0<=X<=100
//        for (int i = 0; i < 1; i++) {
//            value[n][0] = in.nextInt();// 0<= value <24
//            value[n][1] = in.nextInt();//0<= value <60
//        }
//
//        int maxhour = 0;
//        int maxMinu = 0;
//        for (int i = 0; i < n; i++) {
//            int iHour = value[i][0],iMinu =value[i][1];
//            if (iMinu + X >= 60){
//                iHour += 1;
//                iMinu %= 60;
//            }
//            if ((iHour == value[n][0] && iMinu<= value[n][1]) || iHour < value[n][0]){
//                if (value[i][0] > maxhour){
//                    maxhour = value[i][0];
//                    maxMinu = value[i][1];
//                }else if (value[i][0] == maxhour){
//                    if (value[i][1] > maxMinu) maxMinu = value[i][1];
//                }
//            }
//        }
//        System.out.println(maxhour+" "+maxMinu);
        //endregion

    }

*/


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();

        }
    }

}
