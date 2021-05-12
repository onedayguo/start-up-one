package interview;

import java.util.Stack;

/**
 * @Description: 泰为公司面试
 * @Author: kami
 * @Date: 2021/5/12 21:16
 * @Version: 1.0.0
 */
public class Telenav {
    /**
     * @description: 有序数组二分查找目标值，找到返回其下标，找不到返回-1
     * @return: 目标值下标
     * @author: kami
     * @关键词： 二分查找，时间复杂度O（lon N）
     * @date: 2021/5/12 21:26
     */
    private static int binarySearch(int[] arr,int value){
        int left = 0,mid = arr.length >> 2,right = arr.length-1;
        while (arr[mid] != value){
            if (left == mid || right == mid){
                return -1;
            }
            if (arr[mid] > value){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
            mid = (left+right) / 2;
        }
        return mid;
    }
    
    /**
     * @description: Excel列名，A，B....AA，AB.....AAA，AAB.....
     * @return: 列名
     * @author: kami
     * @关键词： 进制转换
     * @date: 2021/5/12 22:00
     */
    private static String getNameByIndex(int index,int N){
        char[] ch = new char[26];
        ch[0] = 'A';
        for (int i = 1; i < 26; i++) {
            ch[i] = (char) (ch[i-1]+1);
        }
        int rest = index;
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(ch[rest % N]);
            rest = rest / N;
        }
        for (; !stack.isEmpty();) {
            result.append(stack.pop());
        }
        return result.length() == 0 ? "0":result.toString();
    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,8,9};
        System.out.println(binarySearch(arr,6));

        String nameByIndex = getNameByIndex(52, 26);
        System.out.println(nameByIndex);
    }
}
