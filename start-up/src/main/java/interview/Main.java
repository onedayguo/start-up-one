package interview;

import java.util.*;

/**
 * 2018/8/11 15.00 - 17.00 服务端开发工程师笔试
 */
public class Main {
    public static void main(String[] args) {

        //int N = 5;
        //int[] arr = {4,5,10,8,15};
        System.out.println(nSum(3,9));

    }
    // 1.
    public static double minDif(int N,int[] arr){
        Arrays.sort(arr);
        double dif = Integer.MAX_VALUE;
        for (int i = 1; i < N-1; i++) {
            double avg = (double) (arr[i-1] + arr[i] + arr[i+1]) / 3;
            double tempDif = Math.pow(arr[i-1]-avg,2) + Math.pow(arr[i]-avg,2) + Math.pow(arr[i+1]-avg,2);
            if (tempDif < dif) dif = tempDif;
        }
        return dif;
    }
    //2.
    public static int nSum(int N,int target){
       if (N == 1) return 1;
       if (N == 2 && target%2 == 0) return target/2 - 1;
       if (N == 2 && target%2 == 1) return target/2;
       int count = nSum(N-1,target-1)+1;
       return count;
    }

    public static int minStep(int L,int N,int[] arr){
        int mid = arr[N/2];
        return 5;
    }
}