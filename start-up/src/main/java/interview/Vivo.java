package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: Vivo2020春季招聘在线笔试题
 * @Auther: kami
 * @Date: 2020/3/8 13:45
 * @Version: 1.0.0
 */
public class Vivo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            int t = in.nextInt();
            //int[] nArray = new int[t];
            for (int i = 0; i < t; i++) {
                int len = in.nextInt();
                String number = in.next();

            }

        }
    }

    /**
     *
     * @param n int整型 第n天
     * @return int整型
     */
    public int solution (int n) {
        // write code here
        if (n == 1) return n;
        int halfN = n / 2;
        while (halfN > 0 ){
            int high = halfN*(halfN+1)/2;
            int low = (halfN-1)*halfN/2;
            int sumSquare = 0;
            if (high >= n && low < n){
                for (int i = 1; i < halfN ; i++) {
                    sumSquare += (i*i);
                }
                return high -(high - n)*halfN;
            }
            else if ( high < n) halfN++;
            else if ( low > n) halfN--;

        }
        return n;
    }
    public int sllution2(int n){
        boolean flag = false;
        while (!flag){
            n++;
            int result = 1;
            while (n / 10 != 0){
                result *= (n % 10);
                n /= 10;

            }
        }

    }
}
