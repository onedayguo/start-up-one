package interview;

import java.util.*;

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
//            int n = Integer.parseInt(in.nextLine());
//            String[] inpuStrArray = new String[n];
//            for (int i = 0; i < n; i++) {
//                inpuStrArray[i] = in.nextLine();
//            }
//            int res = calcMinStaff(n,inpuStrArray);
//            System.out.println(res);

        }

    }


    /**
     * @description: 海豚数量
     * n个海豚，m寿命，birthYear[i]生海豚，x年后海豚数量
     * @return:
     * @auther: kami
     * @date: 2020/4/1 20:18
     */
    public static long countDolphin(int n, int m, int[] birthYear, int x) {
        long count = 0;
        count = backTrack(m,birthYear,x,count);
        return n * count;
    }
    private static long backTrack(int m,int[] birthYear,int newX,long count){
        for (int i = 0; i < birthYear.length; i++) {
            if (birthYear[i] < m && birthYear[i] <= newX){
                count *= 2;
                count += backTrack(m,birthYear,newX-birthYear[i],count);
            }
        }
        return count;
    }

    /**
     * @description: 最少员工数
     * @return:
     * @auther: kami
     * @date: 2020/4/1 20:12
     */
    public static int calcMinStaff(int n,String[] inputStrArray) {
        int[][] intArray = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] temp = inputStrArray[i].split(",");
            intArray[i][0] = Integer.parseInt(temp[0]);
            intArray[i][1] = Integer.parseInt(temp[1]);
        }
//        int minEnd = intArray[0][1];
        int count = 1;
        List<Integer> minEdnToMax = new LinkedList<>();

        minEdnToMax.add(intArray[0][1]);
        for (int i = 1; i < n; i++) {
            if (intArray[i][0] < minEdnToMax.get(0)){
                count++;
            }
            if (intArray[i][1] < minEdnToMax.get(0)){
                minEdnToMax.add(0,intArray[i][1]);
            }else {
                minEdnToMax.add(intArray[i][1]);
                minEdnToMax.sort(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 > o2 ? 1 : 0;
                    }
                });
            }
        }
        return count;
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
