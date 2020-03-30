package interview;

import java.util.Scanner;

/**
 * @Description: 携程
 * @Auther: kami
 * @Date: 2020/3/30 23:01
 * @Version: 1.0.0
 */
public class Ctrip {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int nmk = Integer.parseInt(in.nextLine());
            String nums = in.nextLine();

        }
        Comparable[] a = {15,0,6,9,3};
        sort(a);
        System.out.print("hello,world");
    }

//    public static void

    public static void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h < N / 3) {
            h = 3 * h + 1;// 1, 4, 13, 40, ...
        }
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i;  j >= h && compareElement(a[j],  a[j - h]); j -= h) {
                    exch(a, j, j - h);
                }
            }
            h = h / 3;
        }
    }

    public static boolean compareElement(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
