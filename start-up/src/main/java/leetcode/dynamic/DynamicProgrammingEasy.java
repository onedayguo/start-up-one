package leetcode.dynamic;

/**
 * @Description: 动态规划
 * @Auther: kami
 * @Date: 2020/3/21 10:33
 * @Version: 1.0.0
 */
public class DynamicProgrammingEasy {
    /**
     * @description: 1025. Divisor Game
     * Alice and Bob take turns playing a game, with Alice starting first.
     * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
     * Choosing any x with 0 < x < N and N % x == 0.
     * Replacing the number N on the chalkboard with N - x.
     * Also, if a player cannot make a move, they lose the game.
     * Return True if and only if Alice wins the game, assuming both players play optimally.
     * @return: alice是否赢得游戏
     * @auther: kami
     * @date: 2020/3/21 10:35
     */
    public boolean divisorGame(int N) {
        return  N % 2 == 0;
    }

    /**
     * @description: 121. Best Time to Buy and Sell Stock
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit
     * Note that you cannot sell a stock before you buy one.
     * @return: 获取最大利益
     * @auther: kami
     * @date: 2020/3/21 18:29
     */
    public static int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        int am = maxProfit(arr);
    }
}
