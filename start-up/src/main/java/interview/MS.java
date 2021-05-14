package interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description: 微软
 * @Author: kami
 * @Date: 2021/5/12 10:16
 * @Version: 1.0.0
 */
public class MS {
    /**
     * @discription Given a string s, reverse only all the vowels in the string and return it.
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.
     * @date 2021/5/12 10:17
     **/
    public static void main(String[] args) {
        int[] stocks = {7,1,5,3,6,4};
        System.out.println(getMaxStockPrice(stocks));
        int[] stocks1 = {7,6,4,3,1};
        System.out.println(getMaxStockPrice(stocks1));
        int[] stocks2 = {7,1,4,3,1};
        System.out.println(getMaxStockPrice(stocks2));
        int[] stocks3 = {1,2,3,4,5};
        System.out.println(getMaxStockPrice(stocks3));
        int[] stocks4 = {1,2,0,4,5};
        System.out.println(getMaxStockPrice(stocks4));
        int[] stocks5 = {3};
        System.out.println(getMaxStockPrice(stocks5));
        int[] stocks6 = {3,3,3,3,3};
        System.out.println(getMaxStockPrice(stocks6));
    }
    /**
     * @description: 调换元音字母
     * @author: kami
     * @date: 2021/5/12 11:04
     */
    private static String reverseStr(String str){
        if (str == null || str.length() == 0){
            return "";
        }
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');
        char[] chars = str.toCharArray();
        int size = chars.length;
        int left = 0;
        int right = size-1;
        while (left < right){
            while (left < size && !set.contains(chars[left])){
                left++;
            }
            while (right > -1 && !set.contains(chars[right])){
                right--;
            }
            if (left < right && chars[left] != chars[right]){
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
            }
            left++;
            right--;
        }
        return Arrays.toString(chars);
    }
    /**
     * @description: 买卖一次股票赚的最多钱
     * @author: kami
     * @date: 2021/5/12 11:05
     */
    private static int getMaxStockPrice(int[] stocks){
        if (stocks == null || stocks.length == 0){
            return 0;
        }
        int minPrice = stocks[0];
        int maxProfit = 0;
        for (int i = 0; i < stocks.length; i++) {
            minPrice = Math.min(stocks[i],minPrice);
            maxProfit = Math.max(stocks[i]-minPrice,maxProfit);
        }
        return maxProfit;
    }
    /**
     * @description: 买卖多次股票赚的最多的前
     * @author: kami
     * @date: 2021/5/12 11:05
     */
    private static int getMaxStockPriceMany(int[] stocks){
        int res = 0;
        for (int i = 0; i < stocks.length - 1; i++) {
            if (stocks[i] < stocks[i + 1]) {
                res += (stocks[i + 1] - stocks[i]);
            }
        }
        return res;
    }
}
