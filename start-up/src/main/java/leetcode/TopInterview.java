package leetcode;

/**
 * @Description: LeetCode面试最多,由简单到中等再到困难
 * @Author: kami
 * @Date: 2021/4/26 11:37
 * @Version: 1.0.0
 */
public class TopInterview {
    /**
     * @description: 190. Reverse Bits
     * Reverse bits of a given 32 bits unsigned integer.
     * Note:
     * Note that in some languages such as Java, there is no unsigned integer type. In this case,
     * both input and output will be given as a signed integer type. They should not affect your implementation,
     * as the integer's internal binary representation is the same, whether it is signed or unsigned.
     * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
     * in Example 2 above, the input represents the signed integer -3 and the output represents the signed
     * integer -1073741825.
     * Follow up:
     * If this function is called many times, how would you optimize it?
     * @return: 翻转比特后的数字
     * @author: kami
     * @关键词： 移位
     * @date: 2021/4/26 11:38
     */
    public int reverseBits(int n) {
        int res=0;
        for(int i=0;i<32;i++){
            // 左移扩大
            res = ( res << 1 ) | ( n & 1 );
            n = n >> 1;
        }
        return res;
    }
    /**
     * @description: 326. Power of Three
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3x.
     * @return: n是否是3的幂次
     * @author: kami
     * @关键词： 循环除以3
     * @date: 2021/4/26 12:37
     */
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n > 1){
            if (n%3 != 0){
                return false;
            }
            n /= 3;
        }
        return true;
    }
    /**
     * @description: 172. Factorial Trailing Zeroes
     * Given an integer n, return the number of trailing zeroes in n!.
     * Follow up: Could you write a solution that works in logarithmic time complexity?
     * @return: TODO
     * @author: kami
     * @关键词：TODO
     * @date: 2021/4/26 12:49
     */
    public int trailingZeroes(int n) {
        return 0;
    }
}
