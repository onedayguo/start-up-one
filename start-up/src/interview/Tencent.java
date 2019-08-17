package interview;

import java.io.Serializable;
import java.util.*;

/**
 * @discription
 * @author kami
 * @date 2019/8/17 23:05
 **/
public class Tencent{
    public static void main(String[] args) {
        // [ ] |  HG[3|B[2|CA]]F
        Scanner in = new Scanner(System.in);
        int[][] arr = {
                {3,6},
                {4,7},
                {2,5},
                {0,3},
                {6,9}
        };
        int L = 6;
        System.out.print(minNum(arr,L));


        }

    //4
    /**
     * @discription 腾讯编程第四题，给定一个正整数数组，输出每个位置能够看到其他点的个数，包括自己
     * 例：5 3 8 3 2 5   ----> 3 3 5 4 4 4
     * @date 2019/8/17 23:07
     **/
    public static void seeNum(int[] arr,int n){
        if (n == 1) System.out.print(1);
        else if (n == 2) System.out.print(2+" "+2);
        else {
            for (int i = 0; i < n; i++) {
                int count = 3;
                if (i == 0 || i == n-1) count = 2;

                int leftIndex = i-2;
                int leftMax = 0 ;
                if (i-1 >= 0) leftMax = arr[i-1];
                for (int j = leftIndex; j >= 0 ; j--) {
                    if (arr[j] > leftMax){
                        count++;
                        leftMax = arr[j];
                    }
                }

                int rightIndex = i+2;
                int rightMax = 0;
                if (i+1 < n) rightMax = arr[i+1];
                for (int j = rightIndex; j < n; j++) {
                    if (arr[j] > rightMax){
                        count++;
                        rightMax = arr[j];
                    }
                }

                System.out.print(count+" ");

            }

        }
    }


    //3.
    /**
     * @discription 给定一系列范围，和目标范围，找出至少需要几个范围能覆盖目标范围,0-L
     * @date 2019/8/17 23:26
     **/
    public static int minNum(int[][] arr,int L){
        int right = 0; //距离 L
        int count = 0;
        while (right < L){
            int newRight = right;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i][0] <= right && arr[i][1] > newRight){
                    newRight = arr[i][1];
                }
            }
            right = newRight;
            count++;
        }
        return count;
    }

    //1
//    public static String transfer(String s){
//        if (s==null || s.length() == 0) return "";
//        int len = s.length();
//        int count = 0;
//        for (int i = 0; i < len; i++) {
//            if (s.charAt(i) == '[') count++;
//        }
//        for (int i = 0; i < ; i++) {
//
//        }
//    }

    // 3.

}
