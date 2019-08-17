package leetcode.interest;

import java.util.Scanner;

public class Like {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){

        }

    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * 思路：从右上角开始寻找，向左下角缩小
     *   小----<--大
     *   ^ | | | | ^
     *   大 -------大
     */
    public static boolean find(int target,int[][] arr){
        if (arr == null || arr.length == 0) return false;
        int widthIndex = arr[0].length - 1;
        int heightIndex = 0;
        int height = arr.length - 1;
        while (widthIndex >=0 && heightIndex <= height){
            if (arr[widthIndex][heightIndex] == target) return true;
            else if (arr[widthIndex][heightIndex] < target){
                heightIndex++;
            }else {
                widthIndex--;
            }
        }
        return false;
    }

    /** 4.
     * 给定一个可能含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
     * @param arr
     * @param n
     */
    public static void smallerLeftRight(int[] arr,int n){
        for (int i = 0; i < n; i++) {
            int left = i-1,right = i+1;
            while (left >= 0 && arr[left] >= arr[i]){
                left--;
            }
            if (left < 0) System.out.print(-1 +" ");
            else System.out.print(left+" ");

            while (right < n && arr[right] >= arr[i]){
                right++;
            }
            if (right >= n) System.out.print(-1+" ");
            else System.out.print(right+" ");

            System.out.println();
        }
    }

    /**
     * 1.给定整数序列求最大连续字串和
     * 问题描述：
     * 给定无序整数序列，求连续字串最大和，例如：{-23,17,-7,11,-2,1,34}，字串为{17,-7,11,}，最大和为21
     */
    public static int getTargetMax(int[] arr) {
        int max = arr[0];
        int tmpMax = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (tmpMax <= 0)
                tmpMax = arr[i];
            else
                tmpMax += arr[i];

            if (tmpMax > max) {
                max = tmpMax;
            }
        }
        return max;
    }

    /** 2.
     * 得到字符串中最长回文字符串的长度
     * 首先反转字符串，然后原串和反转串从头到尾一一比较
     * 使用动态规划的思想，利用二维数组保存原串和反转串字符位置对应的最长子回文串长度
     * 二维数组的对应位置的值 是根据当前字符比较的结果 然后在根据二维数组前一位置的值 做累加
     * @param str
     * @return
     */
    public static int getResult(String str){
        StringBuilder sb  = new StringBuilder(str);
        String newStr = sb.reverse().toString();
        char[] c1 = str.toCharArray();
        char[] c2 = newStr.toCharArray();
        int n = str.length();
        int[][] dp = new int[n+1][n+1]; //dp[i][j]代表串1第 i 个字符，到串2第 j 个字符 对应最长字符串长度
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(c1[i-1]==c2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1; // 在上一基础上 +1
                }else{
                    //保持最大长度不变
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[n][n];
    }

    /** 3.
     * 把一个字符串的大写字母放到字符串的后面，各个字符的相对位置不变，且不能申请额外的空间
     * 例子： adDMdkcFVidn      -->   addkcDMFV
     * 遍历2遍字符串， 1.输出小写字母   2.输出大写字母
     * @param s
     * @return String
     */
    public static void moveUpperCase(String s){
        int len = s.length();
        if (len == 0) return;
        for (int i = 0; i < len; i++) {
            char iChar = s.charAt(i);
            if ('a'<= iChar && iChar <= 'z'){
                System.out.print(iChar);
            }
        }
        for (int i = 0; i < len; i++) {
            char iChar = s.charAt(i);
            if ('A' <= iChar && iChar <= 'Z'){
                System.out.print(iChar);
            }
        }
    }

    public static int equalMinDifNum(int[] nums){
        return 1;
    }
}
