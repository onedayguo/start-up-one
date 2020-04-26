package leetcode.interest;

import java.util.*;
import java.util.stream.Stream;

/**
 * @Description: LeetCode30天挑战赛，每天一道题，UTC-8零点开始
 * @Auther: kami
 * @Date: 2020/4/2 22:44
 * @Version: 1.0.0
 */
public class LeetCodeCompetition {
    /**
     * @description: Happy Number
     * Write an algorithm to determine if a number is "happy".
     * A happy number is a number defined by the following process: Starting with any positive integer, replace the number
     * by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or
     * it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
     * @return:
     * @auther: kami
     * @date: 2020/4/3 8:03
     */
    public static boolean isHappy(int n) {
        boolean notHappy = true;
        char[] nChar = String.valueOf(n).toCharArray();
        Set<Integer> set = new HashSet<>();
        while (notHappy){
            int sum = 0;
            for (char i:nChar) {
                sum += Math.pow(i-'0',2);
            }
            if (sum == 1) return true;
            if (set.contains(sum)){
                return false;
            }else {
                set.add(sum);
            }
            nChar = String.valueOf(sum).toCharArray();
        }
        return false;
    }

    /**
     * @description:  Single Number
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * @return:
     * @auther: kami
     * @date: 2020/4/2 22:48
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i:nums) {
            res ^= i;
        }
        return res;
    }

    public int singleNumber1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[0] ^= nums[i];
        }
        return nums[0];
    }

    /**
     * @description: Maximum Subarray
     * Given an integer array nums, find the contiguous subarray (containing at least one number)
     * which has the largest sum and return its sum.
     * If you have figured out the O(n) solution, try coding another solution using the divide and conquer
     * approach, which is more subtle.
     * @return: 子序列最大和
     * @auther: kami
     * @date: 2020/4/5 17:13
     */
    public int maxSubArrayBase(int[] nums) {
        int sum = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                sum = Math.max(temp,sum);
            }
        }
        return sum;
    }

    /**
     * @description: 动态规划版
     * @return:
     * @auther: kami
     * @date: 2020/4/5 18:00
     */
    public int maxSubArrayMid(int[] nums) {
        int maxsum, maxhere;
        maxsum = maxhere = nums[0];   //初始化最大和为a【0】
        for (int i=1; i< nums.length; i++) {
            if (maxhere <= 0)
                maxhere = nums[i];  //如果前面位置最大连续子序列和小于等于0，则以当前位置i结尾的最大连续子序列和为a[i]
            else
                maxhere += nums[i]; //如果前面位置最大连续子序列和大于0，则以当前位置i结尾的最大连续子序列和为它们两者之和
            if (maxhere > maxsum) {
                maxsum = maxhere;  //更新最大连续子序列和
            }
        }
        return maxsum;
    }

    /**
     * @description: Move Zeroes
     * iven an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
     * You must do this in-place without making a copy of the array.
     * Minimize the total number of operations.
     * @return: 把0移动到尾部的数组
     * @auther: kami
     * @date: 2020/4/5 21:54
     */
    public static void moveZeroes(int[] nums) {
        for (int i = 0,j = 0; i < nums.length; ++i) {
            if (nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    /**
     * @description: Best Time to Buy and Sell Stock II
     * Say you have an array for which the ith element is the price of a given stock on day i.
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one
     * and sell one share of the stock multiple times).
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
     * @return: 最大利润
     * @auther: kami
     * @date: 2020/4/6 11:40
     */
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0; i < prices.length-1; i++) {
            if (prices[i] < prices[i+1]){
                res += (prices[i+1]-prices[i]);
            }
        }
        return res;
    }

    /**
     * @description:  Group Anagrams
     * Given an array of strings, group anagrams together.
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     *   ["ate","eat","tea"],
     *   ["nat","tan"],
     *   ["bat"]
     * ]
     * @return:
     * @auther: kami
     * @date: 2020/4/9 21:51
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap<>();
        for (String s:strs) {
            int[] arr = new int[26];
            for (int i = 0,len=s.length(); i < len; i++) {
                arr[s.charAt(i)-'a']++;
            }
            String strKey = Arrays.toString(arr);
            List<String> tempList = map.getOrDefault(strKey, new LinkedList<>());
            tempList.add(s);
            map.put(strKey,tempList);
        }
        return new LinkedList<>(map.values());
    }

    /**
     * @description: Counting Elements
     * Given an integer array arr, count element x such that x + 1 is also in arr.
     * If there're duplicates in arr, count them seperately.
     * @return:
     * @auther: kami
     * @date: 2020/4/11 23:40
     */
    public static int countElements(int[] arr) {
        int count = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i:arr) {
            if (map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }
        for (Map.Entry<Integer, Integer> item:map.entrySet()) {
            if (map.containsKey(item.getKey()+1)){
                count += item.getValue();
            }
        }
        return count;
    }

    static class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
    }
    /**
     * @description:  Middle of the Linked List 双指针--快慢指针
     * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     * If there are two middle nodes, return the second middle node.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 10:55
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next!= null){
            slow = slow.next;
            quick = quick.next.next;
        }
        if (quick.next != null){
            slow = slow.next;
        }
        return slow;
    }

    /**
     * @description: Backspace String Compare
     * Given two strings S and T, return if they are equal when both are typed into empty text editors.
     * # means a backspace character.
     * @return: 
     * @auther: kami
     * @date: 2020/4/12 11:53
     */
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();
        for (int i = 0,len=S.length(); i < len; i++) {
            char ch = S.charAt(i);
            if (ch != '#'){
                sStack.push(ch);
            }else if (!sStack.isEmpty()){
                sStack.pop();
            }
        }
        for (int i = 0,len=T.length(); i < len; i++) {
            char ch = T.charAt(i);
            if (ch != '#'){
                tStack.push(ch);
            }else if (!tStack.isEmpty()){
                tStack.pop();
            }
        }
        return sStack.equals(tStack);
    }

    /**
     * @description: Min Stack
     * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
     * push(x) -- Push element x onto stack.
     * pop() -- Removes the element on top of the stack.
     * top() -- Get the top element.
     * getMin() -- Retrieve the minimum element in the stack.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 12:27
     */
    class MinStack {
        Stack<Integer> stack;
        int min = Integer.MAX_VALUE;
        /** initialize your data structure here. */
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            min = Math.min(x,min);
            stack.push(x);
        }

        public void pop() {
            stack.pop();
            min = Integer.MAX_VALUE;
            for (Integer i:stack) {
                min = Math.min(min,i);
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
    }
    /**
     * @description: Diameter of Binary Tree
     * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree
     * is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
     * @return: 两节点最长距离
     * @auther: kami
     * @date: 2020/4/12 12:28
     */
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return diameter;
    }
    // 此函数是返回树的最大深度
    private int getDepth(TreeNode root) {
        if (root == null)
            return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        diameter = Math.max(diameter, l + r);
        return Math.max(l, r) + 1;
    }

    /**
     * @description: week2.5  Last Stone Weight
     * We have a collection of stones, each stone has a positive integer weight.
     * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y
     * with x <= y.  The result of this smash is:
     * If x == y, both stones are totally destroyed;
     * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
     * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
     * @return: 最后剩余的石头的重量
     * @auther: kami
     * @date: 2020/4/22 22:07
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int weight:stones) {
            queue.add(weight);
        }
        while (queue.size() > 1){
            int max = queue.poll();
            int maxNext = queue.poll();
            if (max != maxNext){
                queue.add(max-maxNext);
            }
        }


        return queue.isEmpty() ? 0:queue.peek();
    }

    /**
     * @description: Contiguous Array
     * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
     *
     * The idea is to change 0 in the original array to -1. Thus, if we find SUM[i, j] == 0 then we know there are even
     * number of -1 and 1 between index i and j. Also put the sum to index mapping to a HashMap to make search faster.
     * @return: 0 1 相同数量最大宽度
     * @auther: kami
     * @date: 2020/4/22 22:46
     */
    public int findMaxLength(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) nums[i] = -1;
        }

        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum)) {
                res = Math.max(res, i-map.get(sum));
            }
            else {
                map.put(sum, i);
            }
        }

        return res;
    }

    /**
     * @description: Perform String Shifts
     * You are given a string s containing lowercase English letters, and a matrix shift, where
     * shift[i] = [direction, amount]:
     * direction can be 0 (for left shift) or 1 (for right shift).
     * amount is the amount by which string s is to be shifted.
     * A left shift by 1 means remove the first character of s and append it to the end.
     * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
     * Return the final string after all operations.
     * 提示：
     * Intuitively performing all shift operations is acceptable due to the constraints.
     * You may notice that left shift cancels the right shift, so count the total left shift times (may be negative if
     * the final result is right shift), and perform it once.
     * @return: 偏移后的字符串
     * @auther: kami
     * @date: 2020/4/23 20:48
     */
    public String stringShift(String s, int[][] shift) {
        int offset = 0;
        for (int i = 0; i < shift.length; i++) {
            if (shift[i][0] == 0){
                offset -= shift[i][1];
            }else {
                offset += shift[i][1];
            }
        }
        int strLen = s.length();
        int absOffset = Math.abs(offset) % strLen;
        if (offset < 0){
            return s.substring(absOffset).concat(s.substring(0,absOffset));
        }else if (offset > 0){
            return s.substring(strLen-absOffset).concat(s.substring(0,strLen-absOffset));
        }
        return s;

    }

    /**
     * @description: Product of Array Except Self
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the
     * product of all the elements of nums except nums[i].
     * onstraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the
     * whole array) fits in a 32 bit integer.
     *
     * Note: Please solve it without division and in O(n).
     *
     * Follow up:
     * Could you solve it with constant space complexity? (The output array does not count as extra space for the
     * purpose of space complexity analysis.)
     * @return: 其他位置相乘之积组成的数组
     * @auther: kami
     * @date: 2020/4/23 21:33
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length, right = 1;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; ++i) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        for (int i = n - 1; i >= 0; --i) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    /**
     * @description: Valid Parenthesis String
     * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether
     * this string is valid. We define the validity of a string by these rules:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     * An empty string is also valid.
     * @return: 是否有效
     * @auther: kami
     * @date: 2020/4/24 21:29
     */
    public boolean checkValidString(String s) {
        return usingStack(s);
    }

    /**
     * @description: Valid Parenthesis String
     * 用一个栈来维护左括号，另一个维护* 的index， 如果栈空时可以启用count来用迄今为止累计的来继续抵消右括号，接替栈的作用。
     * 遍历至结尾需要连续pop出所有还剩下的左括号，用剩下的来match，并保证（出现在 *左边。最后*可以剩下但left不能剩。
     * @return:
     * @auther: kami
     * @date: 2020/4/25 7:56
     */
    private boolean usingStack(String s){
        Stack<Integer> leftIndex = new Stack<>();
        Stack<Integer> startIndex = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            switch (s.charAt(i)){
                case '(':
                    leftIndex.push(i);
                    break;
                case '*':
                    startIndex.push(i);
                    break;
                case ')':
                    if (!leftIndex.isEmpty()){
                        leftIndex.pop();
                    }else if (!startIndex.isEmpty()){
                        startIndex.pop();
                    }else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        while (!leftIndex.isEmpty() && !startIndex.isEmpty()){
            int leftTopIndex = leftIndex.pop();
            int startTopIndex = startIndex.pop();
            if (startTopIndex < leftTopIndex) return false;
        }
        return leftIndex.isEmpty();
    }

    /**
     * @description: Valid Parenthesis String
    这里维护了两个变量low和high，其中low表示在有左括号的情况下，将星号当作右括号时左括号的个数(这样做的原因是尽量不多增加右括号的个数)，
    而high表示将星号当作左括号时左括号的个数。是不是很绕，没办法。那么当high小于0时，说明就算把星号都当作左括号了，还是不够抵消右括号，
    返回false。而当low大于0时，说明左括号的个数太多了，没有足够多的右括号来抵消，返回false。那么开始遍历字符串，当遇到左括号时，
    low和high都自增1；当遇到右括号时，只有当low大于0时，low才自减1，保证了low不会小于0，而high直接自减1；当遇到星号时，只有当low大于0时，
    low才自减1，保证了low不会小于0，而high直接自增1，因为high把星号当作左括号。当此时high小于0，说明右括号太多，返回false。当循环退出后，
    我们看low是否为0，参见代码如下：
     * @return:
     * @auther: kami
     * @date: 2020/4/25 8:33
     */
    private boolean usingLowAndHigh(String s){
        int low = 0,high = 0;
        for (char c:s.toCharArray()) {
            if (c == '('){
                high++;
                low++;
            }else if (c == ')'){
                if (low > 0) low--;
                high--;
            }else if (c == '*'){
                if (low > 0) low--;
                high++;
            }
            if (high < 0) return false;
        }
        return  low == 0;
    }

    /**
     * @description: Number of Islands
     * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by
     * water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges
     * of the grid are all surrounded by water.
     * @return: 小岛个数
     * @auther: kami
     * @date: 2020/4/25 9:17
     */
    public int numIslands(char[][] grid) {
        // 参数校验
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 元素默认值是false
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果此位置没有被访问过，并且此位置是岛，广度优先遍历
                if (!visited[i][j] && grid[i][j] == '1') {
                    result++;
                    bfs(grid, visited, i, j);
                }
            }
        }
        return result;
    }

    private void bfs(char[][] grid, boolean[][] visited, int row, int col) {

        if (row >= 0 && row < grid.length // 行合法
                && col >= 0 && col < grid[0].length // 列合法
                && !visited[row][col] // 没有访问过
                && grid[row][col] == '1') { // 是岛上陆地

            // 标记此位置已经访问过了
            visited[row][col] = true;

            // 上
            bfs(grid, visited, row - 1, col);
            // 右
            bfs(grid, visited, row, col + 1);
            // 下
            bfs(grid, visited, row + 1, col);
            // 左
            bfs(grid, visited, row, col - 1);

        }
    }

    /**
     * @description: Minimum Path Sum
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
     * the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     * @return: 从左上到右下最短路径
     * @auther: kami
     * @date: 2020/4/26 7:52
     */
    public int minPathSum(int[][] grid) {
        int[] dp = new int[grid.length];
        dp[0] = grid[0][0];
        for(int i=1; i<grid.length; i++) dp[i] = grid[i][0]+dp[i-1];
        for(int j=1; j<grid[0].length; j++)
            for(int i=0; i<grid.length; i++)
                dp[i] = (i==0 ? dp[i] : Math.min(dp[i], dp[i-1])) + grid[i][j];
        return dp[grid.length-1];
    }
    public static void main(String[] args) {
        LeetCodeCompetition main = new LeetCodeCompetition();
        int[][] shift = {{0,7},{1,7},{1,0},{1,3},{0,3},{0,6},{1,2}};
        System.out.println(main.stringShift("wpdhhcj",shift));
    }

}
