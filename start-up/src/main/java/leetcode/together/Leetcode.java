package leetcode.together;

/**
 * @Description: 一起刷题
 * @Auther: kami
 * @Date: 2020/3/21 20:16
 * @Version: 1.0.0
 */
public class Leetcode {

    /**
     * @description: 365. Water and Jug Problem 水壶问题
     * you are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
     * You need to determine whether it is possible to measure exactly z litres using these two jugs.
     * If z liters of water is measurable, you must have z liters of water contained within one or both buckets by the end.
     * Operations allowed:
     * Fill any of the jugs completely with water.
     * Empty any of the jugs.
     * Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.
     * @return: 是否能够盛出Z升水
     * @auther: kami
     * @date: 2020/3/21 20:19
     */
    public static boolean canMeasureWater(int x, int y, int z) {
        if (x+y < z) return false;
        if (x == z || y == z || x+y == z) return true;
        int gcd = gcd(x,y);
        return z%gcd(x,y) == 0;

    }
    public static int gcd(int a,int b){
        if (b == 0) return a;
        return gcd(b,a%b);
    }

    public static void main(String[] args) {
        canMeasureWater(34,4,6);
    }
}
