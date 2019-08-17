package interview;
import java.io.IOException;

import java.io.Serializable;
import java.util.*;

public class Tencent implements Serializable {
    public static void main(String[] args) {
        // [ ] |  HG[3|B[2|CA]]F
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()){
//3.
//            int N = in.nextInt();
//            int L = in.nextInt();
//            int[][] arr = new int[N][2];
//            for (int i = 0; i < N; i++) {
//                arr[i][0] = in.nextInt();
//                arr[i][1] = in.nextInt();
//            }
//            System.out.println(minNum(arr,N,L));
            //4.
            int n = in.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = in.nextInt();
            }
            seeNum1(arr,n);


        }
    }
    //4.
    public static void seeNum1(int[] arr,int n){
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
                    if (arr[leftIndex] > leftMax){
                        count++;
                        leftMax = arr[leftIndex];
                    }
                }

                int rightIndex = i+2;
                int rightMax = 0;
                if (i+1 < n) rightMax = arr[i+1];
                for (int j = rightIndex; j < n; j++) {
                    if (arr[rightIndex] > rightMax){
                        count++;
                        rightMax = arr[rightIndex];
                    }
                }

                System.out.print(count+" ");

            }

        }
    }
    public static void seeNum(int[] arr, int n){
        if (n == 1) {
            System.out.print(1);
            return;
        }
        if (n==2) {
            System.out.print(2+" "+2);
            return;
        }
        for (int i = 0; i < n; i++) {
            int count = 2;
            int leftIndex = i-2;
            int rightIndex = i+2;

            if (i == 0){
                count = 2;
                int left = 1;
                int right = 2;
                while (right < n && arr[right] > arr[left]){
                    count++;
                    left = right;
                    right++;

                }
                System.out.print(count);
                continue;

            }
            if (i == 1){
                count = 3;
                int left = 2;
                int right = 3;
                while (right < n && arr[right] > arr[left]){
                    count++;
                    left = right;
                    right++;
                }
                System.out.print(count);
                continue;
            }
            int leftMax = arr[i-1];
            for (int j = leftIndex; j >=0 ; j--) {
                if (arr[leftIndex] > leftMax){
                    count++;
                    leftMax = arr[leftIndex];
                }
            }

            int rightMax = arr[i+1];
            if (i < n - 1){
                 rightMax = arr[i];
            }
            for (int j = rightIndex; j < n; j++) {
                if (arr[rightIndex] > rightMax){
                    count++;
                    rightMax = arr[rightIndex];
                }
            }
            System.out.print(count);
        }
    }

    //3.
    public static int minNum(int[][] arr,int N,int L){
        int right = 0; //距离 L
        int count = 0;
        while (right < L){
            int newRight = right;
            for (int i = 0; i < N; i++) {
                if (arr[i][0] <= right && arr[i][1] > newRight){
                    newRight = arr[i][1];
                }
            }
            right = newRight;
            count++;
        }
        return count;
    }

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
