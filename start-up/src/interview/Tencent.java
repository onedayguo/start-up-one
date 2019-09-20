package interview;

import java.io.Serializable;
import java.util.*;

/**
 * @discription 腾讯面试题2019校园招聘第一次
 * @author kami
 * @date 2019/8/17 23:05
 **/
/**
 * @discription 腾讯面试题2019校园招聘第二次
 * @date 2019/9/1 19:58
 **/
public class Tencent{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
            int t = in.nextInt();
            //int[] nArray = new int[t];
            for (int i = 0; i < t; i++) {
                int len = in.nextInt();
                String number = in.next();
                System.out.println(toKingNumber(len,number));
            }

        }
//        while (in.hasNextLine()){
//            int n = in.nextInt();
//            int m = in.nextInt();
//            int[] boxes = new int[n];
//            for (int i = 0; i < n; i++) {
//                boxes[i] = in.nextInt();
//            }
//            int[] keys = new int[m];
//            for (int i = 0; i < m; i++) {
//                keys[i] = in.nextInt();
//            }
//            maxBox(boxes,keys);
//        }
    }

    public static String toKingNumber(int n,String number){
        if (n < 11) return "NO";
        else {
            int count = 0;
            for (int i = 0; i < n; i++) {
                if (number.charAt(i) - '8' == 0) count++;
                if (count >= 11) return "YES";
            }
            return "NO";
        }
    }

    // 3.
    public static void minTime(int offices,int workers,int[] boxes){
        int sum = 0;
        int count = 0;
        for (int i = 0; i < offices; i++) {
            count++;
            while (workers - boxes[i] < 0){

            }
            workers -= boxes[i];
        }
    }

    // 4.    7 2 4 6 5
    public static void maxScore(int[] scores,int n){
        int left = 0;
        int right = 0;
        int[][] indeAndValue = new int[n][2];
        for (int i = 0; i < n; i++) {

        }
    }

    // 2
    public static void minUnsatify(int[][] aray){

    }



    public static void maxBox(int[] boxes,int[] keys){
        int lenBoxes = boxes.length;
        int lenKeys = keys.length;
        boolean[] used = new boolean[lenKeys];
        int count = 0;
        for (int i = 0; i < lenBoxes; i++) {
            for (int j = 0; j < lenKeys; j++) {
                if (used[j]) continue;
                if ((boxes[i] + keys[j]) % 2 == 1 ){
                    count++;
                    used[j] = true;
                    break;
                }
            }

        }
        System.out.println(count);
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

    //1  HG[3|B[2|CA]]F
    /**
     * @discription 给定某种格式的字符串，还原字符串，如 HG[3|B[2|CA]]F，数字代表 | 后面字符的个数，入参字符串只有 [ , ], |, 数字，字母
     *  G[3|B[2|CA]]F  ----> 还原为 GBCACABCACABCACAF
     * @date 2019/8/18 22:06
     **/
    public static void decryptString(String s){
        if (s == null || s.length() == 0) return ;
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {

        }


    }

    // 3.

}
