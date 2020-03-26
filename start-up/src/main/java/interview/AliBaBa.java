package interview;
import java.util.Scanner;
/**
 * @Description: 阿里巴巴
 * @Auther: kami
 * @Date: 2020/3/26 10:20
 * @Version: 1.0.0
 */
public class AliBaBa {


    static int mod = 1000000007;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        System.out.println((pow(n - 1) * n) % mod);
    }
    
    /**
     * @description: 排列组合
     * 链接：https://www.nowcoder.com/discuss/389778
     * 1、从n个人中选择任意数量的人员组成一支队伍，然后从一支队伍中选出一位队长，不同的队长算不同的组合，问这样的组合的数量对10^9+7取模 。
     * 数据范围：1 <= n <= 1000000000;
     * 输入：n = 2
     * 输出：4
     * 解释,(1),(2)(1,2),(2,1)四种，括号第一个为队长
     * 思路:
     * 首先一看数据范围，应该要O(logN)级别的方法才能AC,分析问题首先应该是个排列组合问题，得到通项公式为：
     * $ res = \sum_{i=1}^ni*C_n^i res=∑
     * 思路1：可以暴力算，当然不推荐，算了也是白算
     * 思路2：动态规划，没写出来，而且也达不到O(logN)复杂度
     * 思路3：数学知识告诉我们，res的通项公式为：
     * $ n*2^{n-1} n∗2
     * n−1
     * 要求2^n - 1，O(logN)复杂度，经典的快速幂算法。
     * @return: 组合个数
     * @auther: kami
     * @date: 2020/3/26 10:21
     */
    public static long pow(int n) {
        if (n == 0)
            return 1;
        long half = pow(n / 2);
        if (n % 2 == 0)
            return (half * half) % mod;
        else
            return (half * half * 2) % mod;
    }

}

