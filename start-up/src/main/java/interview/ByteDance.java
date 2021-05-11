package interview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @Description: 字节跳动
 * @Author: kami
 * @Date: 2020/6/21 13:28
 * @Version: 1.0.0
 */
public class ByteDance {
    /*
        public static void main(String[] args) {
            //region 3.0
    //        Scanner in = new Scanner(System.in);
    //        System.out.println("输入正整数N值：");
    //        int N = in.nextInt();
    //        int[] year = new int[N];
    //        for(int i = 0; i < N; i++){
    //            year[i] = in.nextInt();
    //        }
    //        int sum = 0;
    //        int[] money = new int[N];
    //        for (int i = 0; i < N-1; i++) {
    //            if (year[i] >= year[i+1]){
    //                int start = i;
    //                while (year[start] >= year[start+1] && start+1 < N){
    //                    start++;
    //                }
    //                money[start] = 100;
    //                for (int j = start; j > i; j--) {
    //                    if (year[j-1] > year[j]) money[j-1] = money[j]+100;
    //                    if (year[j-1] == year[i]) money[j-1] = money[j];
    //                }
    //            }
    //            if (year[i] < year[i+1]) {
    //                if (i == 0) year[i] = 100;
    //                else year[i] = money[i-1] + 100;
    //            }
    //        }
    //        for (int i = 0; i < N; i++) {
    //            sum += money[i];
    //        }
    //        System.out.println(sum);
            //endregion


             //2.0
            Scanner in = new Scanner(System.in);
            System.out.println("输入N和K值：");
            int N,K;
            for(int i = 0; i < 1; i++){
                N = in.nextInt();//长度N
                K = in.nextInt();//写下K次
            }
            System.out.println("请输入二进制字符串，长度为 N+K-1：");
            String binaryStr = in.next();//长度 N+K-1

            //region 1.0
    //        Scanner in = new Scanner(System.in);
    //        System.out.println("输入N值：");
    //        int n = in.nextInt();
    //        System.out.println("输入时间值：");
    //        int[][] value = new int[n+1][2];
    //        for(int i = 0; i < n; i++){
    //            value[i][0] = in.nextInt();// 0<= value <24
    //            value[i][1] = in.nextInt();//0<= value <60
    //        }
    //        int X = in.nextInt(); //到教室需要时间  0<=X<=100
    //        for (int i = 0; i < 1; i++) {
    //            value[n][0] = in.nextInt();// 0<= value <24
    //            value[n][1] = in.nextInt();//0<= value <60
    //        }
    //
    //        int maxhour = 0;
    //        int maxMinu = 0;
    //        for (int i = 0; i < n; i++) {
    //            int iHour = value[i][0],iMinu =value[i][1];
    //            if (iMinu + X >= 60){
    //                iHour += 1;
    //                iMinu %= 60;
    //            }
    //            if ((iHour == value[n][0] && iMinu<= value[n][1]) || iHour < value[n][0]){
    //                if (value[i][0] > maxhour){
    //                    maxhour = value[i][0];
    //                    maxMinu = value[i][1];
    //                }else if (value[i][0] == maxhour){
    //                    if (value[i][1] > maxMinu) maxMinu = value[i][1];
    //                }
    //            }
    //        }
    //        System.out.println(maxhour+" "+maxMinu);
            //endregion

        }

    */
    private int getMaxOnlineUsers(List<UserLoginRecord> records) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long startTimeDay = cal.getTimeInMillis() / 1000;
        // 登入登出记录
        int[] logRecord = new int[86400];
        // 每一秒钟的在线人数
        int[] onlineUserRecord = new int[86400];
        for (UserLoginRecord record : records) {
            logRecord[(int) (record.getLoginTime() - startTimeDay)] += 1;
            logRecord[(int) (record.getLogoutTime() - startTimeDay)] -= 1;
        }
        onlineUserRecord[0] = logRecord[0];
        int maxOnline = 0;
        for (int i = 1; i < 86400; i++) {
            onlineUserRecord[i] = onlineUserRecord[i - 1] + logRecord[i];
            maxOnline = Math.max(maxOnline, onlineUserRecord[i]);
        }
        return maxOnline;
    }

    static class UserLoginRecord {
        Long userId;
        Long loginTime;
        Long logoutTime;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(Long loginTime) {
            this.loginTime = loginTime;
        }

        public Long getLogoutTime() {
            return logoutTime;
        }

        public void setLogoutTime(Long logoutTime) {
            this.logoutTime = logoutTime;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * @description: 117. Populating Next Right Pointers in Each Node II
     * Given a binary tree
     * <p>
     * struct Node {
     * int val;
     * Node *left;
     * Node *right;
     * Node *next;
     * }
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * Initially, all next pointers are set to NULL.
     * Follow up:
     * You may only use constant extra space.
     * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
     * 这道题希望我们把二叉树各个层的点组织成链表，一个非常直观的思路是层次遍历。树的层次遍历基于广度优先搜索，它按照层的顺序遍历二叉树，
     * 在遍历第 i 层前，一定会遍历完第 i−1 层。
     * 算法如下：初始化一个队列 qq，将根结点放入队列中。当队列不为空的时候，记录当前队列大小为 n，从队列中以此取出 n 个元素并通过这 n
     * 个元素拓展新节点。如此循环，直到队列为空。我们不难写出这样的代码：
     * @return: 填充了next指针的头节点
     * @author: kami
     * @关键词： 广度优先遍历使用队列额外存储
     * @date: 2021/5/1 8:54
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node last = null;
            for (int i = 1; i <= n; ++i) {
                Node f = queue.poll();
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }

    /**
     * @description: 字节面试，查找所有的组合
     * @return: 组合列表
     * @author: kami
     * @关键词： 全排列
     * @date: 2021/5/7 18:01
     */
    public List<List<Integer>> findAllCombination(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        res.add(new LinkedList<>());
        backtrack(res, nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, int index, List<Integer> cur) {
        if (cur.size() > 0) {
            res.add(new ArrayList<>(cur));
        }
        for (int i = index; i < nums.length; i++) {
            cur.add(nums[i]);
            backtrack(res, nums, i + 1, cur);
            cur.remove(cur.size() - 1);
        }
    }
    /**
     * @description: 获取数组中连续递增子序列的最大长度
     * @return: 子序列长度
     * @author: kami
     * @关键词： O（n）时间复杂度
     * @date: 2021/5/8 10:02
     */
    public int getContinusIncrementNums(int[] nums){
        // key: nums[i],value:i
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            int curValue = nums[i]+1;
            int curIndex = i+1;
            int cnt = 1;
            while (map.getOrDefault(curValue++,-1) == curIndex++ ){
                cnt++;
            }
            max = Math.max(max,cnt);
        }
        return max;
    }

    /**
     * @description: 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
     * 输入: 2
     * 输出: 2
     * 解释：有2种方案。分别是0->1->0和0->9->0
     * @return: 回到原点的走法
     * @author: kami
     * @关键词： 动态规划
     * @date: 2021/5/9 19:50
     */
    public int backToStartWays(int n){
        int[][] dp = new int[n+1][11];
        dp[0][0] = 1;
        dp[2][0] = 2;
        int length = 10;
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < length; j++) {
                dp[i][j] = dp[i-1][(j-1+length)%length] + dp[i-1][(j+1)%length];
            }
        }
        return dp[n][0];
    }
    /**
     * @description: 找到比给定数字大的最小的回文数字
     * @return: 回文数
     * @author: kami
     * @关键词： 从中间分开
     *  A: 如果该数字串数字个数>1, 则按下面处理：
     *  1:如果该数的位数是偶数，把左数字串反过来，形成一个新的右数字串。
     *     1.1如果该新的右数字串换成数字后，比原来的右数字串大，则原来的左数字串+新的右数字串=所求之数字串。
     *     1.2 否则，把左数字加1，重新生成为一个新的左数字串，然后把它反转，形成新的右数字串。
     *         则 新的左数字串 + 新的右数字串 = 所求之数字串。
     *
     *    2 如果该数的位数是奇数，把左数字串反过来，形成一个新的右数字串。
     *      2.1如果该新的右数字串换成数字后，比原来的右数字串大， 则 原来的左数字串 + 新的右数字串 = 所求之数字串。
     *      2.2 否则，把中间的那个数字+1。
     *           2.2.1 如果该中间数字加1后大于9，则把原来的左数字+1，然后形成一个新的左数字串,；如果该中间数字加1后<=9，原来的左数字串不变。
     *          2.2.2 把上步中的左数字串反转，形成新的右数字串。
     *          2.2.3 合并上面步骤得到的左数字串+中间数字+右数字串，即为所求之数字串。
     *
     *  B: 如果数字串数字个数=1，则按以下处理：
     *  如果该数字<9,则，该数字+1后为所求的数字。
     *  如果该数字=9,则11为所求的数字。
     * @date: 2021/5/10 15:08
     */
    public String findMinPalindromeGreaterThan(int num){
        String str = String.valueOf(num);
        int len = str.length();
        // 偶数
        boolean even = len % 2 == 0;
        int half = len / 2;
        String left = str.substring(0,half);
        int leftNum = Integer.parseInt(left);
        String right = str.substring(half);
        int rightNum = Integer.parseInt(right);
        String mid = str.substring(half,half+1);
        int midNum = Integer.parseInt(mid);
        if (num < 9){
            return String.valueOf(num+1);
        }else if (num<11){
            return "11";
        }else if (even){
            // 偶数长度
            String reverseLeft = new StringBuilder(left).reverse().toString();
            if (Integer.parseInt(reverseLeft) > rightNum){
                return left+reverseLeft;
            }else {
                left = left+1;
                String reverseLeft1 = new StringBuilder(left).reverse().toString();
                return left+reverseLeft1;
            }
        }else {
            // 奇数长度
            String reverseLeft3 = new StringBuilder(left).reverse().toString();
            if (Integer.parseInt(reverseLeft3) > rightNum){
                return left+mid+reverseLeft3;
            }else {
                int newMid = Integer.parseInt(mid)+1;
                if (newMid > 9){
                    int newLeft = leftNum+1;
                    String reverseLeft4 = new StringBuilder(newLeft).reverse().toString();
                    return newLeft+newLeft+reverseLeft4;
                }
            }

        }
        return "0";
    }
        

    public static void main(String[] args) {
        ByteDance b = new ByteDance();
        int[] nu = {1, 4,3,7,5,6,8,9,10};
        int continusIncrementNums = b.getContinusIncrementNums(nu);
        System.out.println(continusIncrementNums);
    }

}
