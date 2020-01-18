package frame.interview;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: kami
 * @Date: 2019/9/1 14:17
 * @Description: 拼多多
 */
public class PinDuoDuo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();
            int k = in.nextInt();
            kMaxNumber(n,m,k);
        }

        while (in.hasNextLine()){
            String data = in.nextLine();
            String[] arrayStr = data.split(",");
            int len = arrayStr.length;
            int n = Integer.valueOf(arrayStr[len-1].split(";")[1]);
            arrayStr[len-1] = arrayStr[len-1].split(";")[0];
            int[] arrayInt = new int[len];
            for (int i = 0; i < len; i++) {
                arrayInt[i] = Integer.valueOf(arrayStr[i]);
            }
            sortNumber(arrayInt,n);
        }


    }

    public static void kMaxNumber(int n,int m,int k){
        int max = 0;
        for (int i = 0; i <= k ; i++) {
            for (int j = 0; j <= k ; j++) {
                if (i+j == k/2){
                    int indexN = n - i;
                    int indexM = m - j;
                    int sum = indexN*indexM;
                    if (sum > max) max = sum;
                }
            }
        }
        System.out.print(max);
    }

    public static void sortNumber(int[] array,int n){
        int len = array.length-1;
        Arrays.sort(array);
        int[] res = new int[n];
        int index = 0;
        for (int i = len; i >=0 ; i--) {
            if (array[i] % 2 == 0){
                if (index == n) break;
                res[index++] = array[i];
            }
        }
        for (int i = len; i >= 0; i--) {
            if (index == n) break;
            if (array[i] % 2 != 0){
                res[index++] = array[i];
            }
        }

        for (int i = 0,resLen = res.length; i < resLen; i++) {
            if (i == resLen - 1) System.out.print(res[i]);
            else System.out.print(res[i]+",");

        }
    }

}


