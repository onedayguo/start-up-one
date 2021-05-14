package leetcode.interest;

import javax.swing.plaf.IconUIResource;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

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
        while (notHappy) {
            int sum = 0;
            for (char i : nChar) {
                sum += Math.pow(i - '0', 2);
            }
            if (sum == 1) return true;
            if (set.contains(sum)) {
                return false;
            } else {
                set.add(sum);
            }
            nChar = String.valueOf(sum).toCharArray();
        }
        return false;
    }

    /**
     * @description: Single Number
     * Given a non-empty array of integers, every element appears twice except for one. Find that single one.
     * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
     * @return:
     * @auther: kami
     * @date: 2020/4/2 22:48
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i : nums) {
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
                sum = Math.max(temp, sum);
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
        for (int i = 1; i < nums.length; i++) {
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
        for (int i = 0, j = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
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
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i] < prices[i + 1]) {
                res += (prices[i + 1] - prices[i]);
            }
        }
        return res;
    }

    /**
     * @description: Group Anagrams
     * Given an array of strings, group anagrams together.
     * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * Output:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * @return:
     * @auther: kami
     * @date: 2020/4/9 21:51
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            int[] arr = new int[26];
            for (int i = 0, len = s.length(); i < len; i++) {
                arr[s.charAt(i) - 'a']++;
            }
            String strKey = Arrays.toString(arr);
            List<String> tempList = map.getOrDefault(strKey, new LinkedList<>());
            tempList.add(s);
            map.put(strKey, tempList);
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
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            if (map.containsKey(item.getKey() + 1)) {
                count += item.getValue();
            }
        }
        return count;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * @description: Middle of the Linked List 双指针--快慢指针
     * Given a non-empty, singly linked list with head node head, return a middle node of linked list.
     * If there are two middle nodes, return the second middle node.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 10:55
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode quick = head;
        while (quick.next != null && quick.next.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        if (quick.next != null) {
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
        for (int i = 0, len = S.length(); i < len; i++) {
            char ch = S.charAt(i);
            if (ch != '#') {
                sStack.push(ch);
            } else if (!sStack.isEmpty()) {
                sStack.pop();
            }
        }
        for (int i = 0, len = T.length(); i < len; i++) {
            char ch = T.charAt(i);
            if (ch != '#') {
                tStack.push(ch);
            } else if (!tStack.isEmpty()) {
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

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new Stack<>();
        }

        public void push(int x) {
            min = Math.min(x, min);
            stack.push(x);
        }

        public void pop() {
            stack.pop();
            min = Integer.MAX_VALUE;
            for (Integer i : stack) {
                min = Math.min(min, i);
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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
        for (int weight : stones) {
            queue.add(weight);
        }
        while (queue.size() > 1) {
            int max = queue.poll();
            int maxNext = queue.poll();
            if (max != maxNext) {
                queue.add(max - maxNext);
            }
        }


        return queue.isEmpty() ? 0 : queue.peek();
    }

    /**
     * @description: Contiguous Array
     * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
     * <p>
     * The idea is to change 0 in the original array to -1. Thus, if we find SUM[i, j] == 0 then we know there are even
     * number of -1 and 1 between index i and j. Also put the sum to index mapping to a HashMap to make search faster.
     * @return: 0 1 相同数量最大宽度
     * @auther: kami
     * @date: 2020/4/22 22:46
     */
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) nums[i] = -1;
        }

        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                res = Math.max(res, i - map.get(sum));
            } else {
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
            if (shift[i][0] == 0) {
                offset -= shift[i][1];
            } else {
                offset += shift[i][1];
            }
        }
        int strLen = s.length();
        int absOffset = Math.abs(offset) % strLen;
        if (offset < 0) {
            return s.substring(absOffset).concat(s.substring(0, absOffset));
        } else if (offset > 0) {
            return s.substring(strLen - absOffset).concat(s.substring(0, strLen - absOffset));
        }
        return s;

    }

    /**
     * @description: Product of Array Except Self
     * Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the
     * product of all the elements of nums except nums[i].
     * onstraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the
     * whole array) fits in a 32 bit integer.
     * <p>
     * Note: Please solve it without division and in O(n).
     * <p>
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
     * <p>
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
    private boolean usingStack(String s) {
        Stack<Integer> leftIndex = new Stack<>();
        Stack<Integer> startIndex = new Stack<>();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            switch (s.charAt(i)) {
                case '(':
                    leftIndex.push(i);
                    break;
                case '*':
                    startIndex.push(i);
                    break;
                case ')':
                    if (!leftIndex.isEmpty()) {
                        leftIndex.pop();
                    } else if (!startIndex.isEmpty()) {
                        startIndex.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        while (!leftIndex.isEmpty() && !startIndex.isEmpty()) {
            int leftTopIndex = leftIndex.pop();
            int startTopIndex = startIndex.pop();
            if (startTopIndex < leftTopIndex) return false;
        }
        return leftIndex.isEmpty();
    }

    /**
     * @description: Valid Parenthesis String
     * 这里维护了两个变量low和high，其中low表示在有左括号的情况下，将星号当作右括号时左括号的个数(这样做的原因是尽量不多增加右括号的个数)，
     * 而high表示将星号当作左括号时左括号的个数。是不是很绕，没办法。那么当high小于0时，说明就算把星号都当作左括号了，还是不够抵消右括号，
     * 返回false。而当low大于0时，说明左括号的个数太多了，没有足够多的右括号来抵消，返回false。那么开始遍历字符串，当遇到左括号时，
     * low和high都自增1；当遇到右括号时，只有当low大于0时，low才自减1，保证了low不会小于0，而high直接自减1；当遇到星号时，只有当low大于0时，
     * low才自减1，保证了low不会小于0，而high直接自增1，因为high把星号当作左括号。当此时high小于0，说明右括号太多，返回false。当循环退出后，
     * 我们看low是否为0，参见代码如下：
     * @return:
     * @auther: kami
     * @date: 2020/4/25 8:33
     */
    private boolean usingLowAndHigh(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                high++;
                low++;
            } else if (c == ')') {
                if (low > 0) low--;
                high--;
            } else if (c == '*') {
                if (low > 0) low--;
                high++;
            }
            if (high < 0) return false;
        }
        return low == 0;
    }

    /**
     * @description: 200 Number of Islands
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
     * Note: You can only move either down or right at any point in time.
     * @return: 从左上到右下最短路径
     * @auther: kami
     * @date: 2020/4/26 7:52
     */
    public int minPathSum(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        int[] dp = new int[row];
        dp[0] = grid[0][0];
        for (int i = 1; i < row; i++) {
            dp[i] = grid[i][0] + dp[i - 1];
        }
        for (int j = 1; j < col; j++) {
            for (int i = 0; i < col; i++) {
                dp[i] = (i == 0 ? dp[i] : Math.min(dp[i], dp[i - 1])) + grid[i][j];
            }
        }
        return dp[row - 1];
    }

    /**
     * @description: Search in Rotated Sorted Array
     * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
     * You are given a target value to search. If found in the array return its index, otherwise return -1.
     * You may assume no duplicate exists in the array.
     * Your algorithm's runtime complexity must be in the order of O(log n).
     * @return: 目标值的下标索引
     * @auther: kami
     * @date: 2020/4/28 7:26
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (target == nums[m])
                return m;
            if (nums[m] < nums[r]) {
                if (target > nums[m] && target <= nums[r])
                    l = m + 1;
                else
                    r = m - 1;
            } else {
                if (target >= nums[l] && target < nums[m])
                    r = m - 1;
                else
                    l = m + 1;
            }
        }
        return -1;

    }

    /**
     * @description: Construct Binary Search Tree from Preorder Traversal
     * Return the root node of a binary search tree that matches the given preorder traversal.
     * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a
     * value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal
     * displays the value of the node first, then traverses node.left, then traverses node.right.)
     * Note:
     * 1 <= preorder.length <= 100
     * The values of preorder are distinct.
     * @return: 前序遍历的二叉树
     * @auther: kami
     * @date: 2020/4/29 7:06
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        /**
         * @description: 首先将第一个数据root压栈
         *
         * 然后逐个处理剩下的，与当前节点比较（栈顶就是当前处理的节点，因为是刚压进去的）
         * 1）如果比栈顶小，直接放左边
         * 2）否则，就应该放在右边，这时候应该往上找第一个比新数据小的【对应内部while循环】找到后，就放在右边
         * 刚处理的节点压栈，继续处理
         * @return: leetcode.interest.LeetCodeCompetition.TreeNode
         * @auther: kami
         * @date: 2020/4/29 7:46
         */
        if (preorder == null || preorder.length == 0) return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.add(root);    // 根压栈
        for (int i = 1; i < preorder.length; i++) {
            TreeNode node = stack.peek();   // 当前节点
            if (node.val > preorder[i]) {    // 新的数据小于当前节点
                node.left = new TreeNode(preorder[i]);  // 新的节点放左边
                stack.add(node.left);   // 压栈
            } else {
                // 往上找，找到第一个比新数据小的
                while (!stack.isEmpty() && stack.peek().val < preorder[i]) node = stack.pop();
                node.right = new TreeNode(preorder[i]);
                stack.add(node.right);
            }
        }
        return root;
    }

    /**
     * @description: Leftmost Column with at Least a One
     * (This problem is an interactive problem.)
     * A binary matrix means that all elements are 0 or 1. For each individual row of the matrix, this row is sorted
     * in non-decreasing order.
     * Given a row-sorted binary matrix binaryMatrix, return leftmost column index(0-indexed) with at least a 1 in it.
     * If such index doesn't exist, return -1.
     * You can't access the Binary Matrix directly.  You may only access the matrix using a BinaryMatrix interface:
     * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
     * BinaryMatrix.dimensions() returns a list of 2 elements [rows, cols], which means the matrix is rows * cols.
     * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer.  Also, any solutions
     * that attempt to circumvent the judge will result in disqualification.
     * For custom testing purposes you're given the binary matrix mat as input in the following four examples. You will
     * not have access the binary matrix directly.
     * @return:
     * @auther: kami
     * @date: 2020/4/29 20:45
     */
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimensions = binaryMatrix.dimensions();
        int m = dimensions.get(0);
        int n = dimensions.get(1);

        int leftmost = 101;

        for (int i = 0; i < m; i++) {
            int left, right, mid;
            left = 0;
            right = n - 1;
            while (left < right) {
                mid = (left + right) >> 1;
                if (binaryMatrix.get(i, mid) == 1)
                    right = mid;
                else
                    left = mid + 1;
            }
            if (binaryMatrix.get(i, left) == 1 && left < leftmost)
                leftmost = left;
            if (leftmost == 101)
                leftmost = -1;
        }

        return leftmost;
    }

    /**
     * @description: Bitwise AND of Numbers Range
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
     * @return: 相与之后的值
     * @auther: kami
     * @date: 2020/5/5 12:41
     */
    public static int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            ++offset;
        }
        return m << offset;
    }

    /**
     * @description: LruCache的双向链表
     * @return:
     * @auther: kami
     * @date: 2020/5/6 17:09
     */
    class LinkNode {
        Integer key;
        Integer value;
        LinkNode front;
        LinkNode next;

        public LinkNode(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * @description: LRU Cache
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
     * operations: get and put.
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     * it should invalidate the least recently used item before inserting a new item.
     * The cache is initialized with a positive capacity.
     * @return:
     * @auther: kami
     * @date: 2020/5/5 17:31
     */
    class LRUCache {
        private int capacity;
        Map<Integer, LinkNode> map = new HashMap<>();
        LinkNode head = new LinkNode(0, 0); // 虚拟头结点
        LinkNode tail = new LinkNode(0, 0); // 虚拟尾结点

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.front = head;
        }

        public int get(int key) {
            if (map.containsKey(key)) {
                LinkNode node = map.get(key);
                moveToTop(node);
                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            if (!map.containsKey(key)) {
                if (map.size() >= this.capacity) {
                    removeLastNode();
                }

                LinkNode tempSecond = head.next;
                LinkNode newNode = new LinkNode(key, value);
                head.next = newNode;
                newNode.front = head;
                newNode.next = tempSecond;
                tempSecond.front = newNode;

                map.put(key, newNode);
            } else {
                LinkNode node = map.get(key);
                node.value = value;
                moveToTop(node);
            }
        }

        private void removeLastNode() {
            LinkNode lastNode = tail.front;
            lastNode.front.next = tail;
            tail.front = lastNode.front;
            map.remove(lastNode.key);
        }

        private void moveToTop(LinkNode node) {
            node.front.next = node.next;
            node.next.front = node.front;

            LinkNode headNodeSource = head.next;
            head.next = node;
            node.front = head;

            node.next = headNodeSource;
            headNodeSource.front = node;
        }
    }

    /**
     * @description: Jump Game
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * 1 <= nums.length <= 3 * 10^4
     * 0 <= nums[i][j] <= 10^5
     * @return: 能否到达最后
     * @auther: kami
     * @date: 2020/5/6 18:25
     */
    public static boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int reach = 0;

        for (int i = 0; i <= reach && i < nums.length; i++) {
            reach = Math.max(nums[i] + i, reach);
        }
        if (reach < nums.length - 1) return false;
        return true;
    }

    /**
     * @description: Letter Combinations of a Phone Number
     * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number
     * could represent. Return the answer in any order.
     * <p>
     * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does
     * not map to any letters.
     * @return: TODO
     * @author: kami
     * @备注：TODO
     * @date: 2021/4/8 19:26
     */
    public List<String> letterCombinations(String digits) {
        char[] chars = digits.toCharArray();
        if (chars.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>((int) Math.pow(3, chars.length));
        findAllLetters(chars, 0, new String(), res);
        return res;
    }

    private void findAllLetters(char[] chars, int index, String sb, List<String> res) {
        if (sb.length() == chars.length) {
            res.add(sb);
            return;
        }
        char aChar = chars[index++];
        switch (aChar) {
            case '2':
                findAllLetters(chars, index, sb + 'a', res);
                findAllLetters(chars, index, sb + 'b', res);
                findAllLetters(chars, index, sb + 'c', res);
                break;
            case '3':
                findAllLetters(chars, index, sb + 'd', res);
                findAllLetters(chars, index, sb + 'e', res);
                findAllLetters(chars, index, sb + 'f', res);
                break;
            case '4':
                findAllLetters(chars, index, sb + 'g', res);
                findAllLetters(chars, index, sb + 'h', res);
                findAllLetters(chars, index, sb + 'i', res);
                break;
            case '5':
                findAllLetters(chars, index, sb + 'j', res);
                findAllLetters(chars, index, sb + 'k', res);
                findAllLetters(chars, index, sb + 'l', res);
                break;
            case '6':
                findAllLetters(chars, index, sb + 'm', res);
                findAllLetters(chars, index, sb + 'n', res);
                findAllLetters(chars, index, sb + 'o', res);
                break;
            case '7':
                findAllLetters(chars, index, sb + 'p', res);
                findAllLetters(chars, index, sb + 'q', res);
                findAllLetters(chars, index, sb + 'r', res);
                findAllLetters(chars, index, sb + 's', res);
                break;
            case '8':
                findAllLetters(chars, index, sb + 't', res);
                findAllLetters(chars, index, sb + 'u', res);
                findAllLetters(chars, index, sb + 'v', res);
                break;
            default:
                findAllLetters(chars, index, sb + 'w', res);
                findAllLetters(chars, index, sb + 'x', res);
                findAllLetters(chars, index, sb + 'y', res);
                findAllLetters(chars, index, sb + 'z', res);
                break;
        }


    }

    /**
     * @description: 是否是外星文
     * @return: boolean
     * @author: kami
     * @备注： 周比赛
     * @date: 2021/4/9 15:35
     */
    static int[] mapping = new int[26];

    public static boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < order.length(); i++)
            mapping[order.charAt(i) - 'a'] = i;
        for (int i = 1; i < words.length; i++)
            if (bigger(words[i - 1], words[i]))
                return false;
        return true;
    }

    static boolean bigger(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        for (int i = 0; i < n && i < m; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return mapping[s1.charAt(i) - 'a'] > mapping[s2.charAt(i) - 'a'];
            }
        }
        return n > m;
    }

    /**
     * @description: Longest Increasing Path in a Matrix
     * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
     * <p>
     * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally
     * or move outside the boundary (i.e., wrap-around is not allowed).
     * @return: 最长递增路径
     * @author: kami
     * @备注： 递归
     * @date: 2021/4/11 19:57
     */
    public int longestIncreasingPath(int[][] matrix) {
        int count = 1;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                count = Math.max(count, getMaxPath(dp, matrix, i, j));
            }
        }
        return count;

    }

    private int getMaxPath(int[][] dp, int[][] matrix, int row, int col) {
        if (dp[row][col] > 0) {
            return dp[row][col];
        }
        int max = 1;
        // 上
        if (row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col]) {
            max = Math.max(max, 1 + getMaxPath(dp, matrix, row - 1, col));
        }
        // 下
        if (row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col]) {
            max = Math.max(max, 1 + getMaxPath(dp, matrix, row + 1, col));
        }
        // 左
        if (col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col]) {
            max = Math.max(max, 1 + getMaxPath(dp, matrix, row, col - 1));
        }
        // 右
        if (col + 1 < matrix[0].length && matrix[row][col + 1] > matrix[row][col]) {
            max = Math.max(max, 1 + getMaxPath(dp, matrix, row, col + 1));
        }
        dp[row][col] = max;
        return max;

    }

    /**
     * @description: Deepest Leaves Sum
     * Given the root of a binary tree, return the sum of values of its deepest leaves.
     * @return: 路径最深的叶子节点之和
     * @author: kami
     * @备注： 可能会有多个相同深度的叶子节点
     * @date: 2021/4/12 8:05
     */
    public int deepestLeavesSum(TreeNode root) {
        int res = 0, i;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            for (i = q.size() - 1, res = 0; i >= 0; --i) {
                TreeNode node = q.poll();
                res += node.val;
                if (node.right != null) {
                    q.add(node.right);
                }
                if (node.left != null) {
                    q.add(node.left);
                }
            }
        }
        return res;
    }

    /**
     * @description: Palindrome Linked List
     * Given the head of a singly linked list, return true if it is a palindrome.
     * @return: 是否是回文链表
     * @author: kami
     * @备注：
     * @date: 2021/4/12 16:07
     */
    public static boolean isPalindrome(ListNode head) {
        int count = 0;
        ListNode curNode = head;
        while (curNode != null) {
            count++;
            curNode = curNode.next;
        }
        // 长度为奇偶
        int enen = count % 2;
        int half = count / 2;
        ListNode rightReverseHead = null;
        if (enen == 0) {
            // 偶数
            ListNode curNode1 = head;
            for (int i = 0; i <= half; i++) {
                if (i == half) {
                    rightReverseHead = reverseListNode(curNode1);
                }
                curNode1 = curNode1.next;
            }
        } else {
            ListNode curNode2 = head;
            for (int i = 0; i <= half; i++) {
                if (i == half) {
                    rightReverseHead = reverseListNode(curNode2);
                }
                curNode2 = curNode2.next;
            }
        }
        boolean isPalindrome = true;
        while (rightReverseHead != null) {
            if (rightReverseHead.val != head.val) {
                return false;
            }
            rightReverseHead = rightReverseHead.next;
            head = head.next;
        }
        return isPalindrome;
    }

    private static ListNode reverseListNode(ListNode head) {
        ListNode cur = head;
        ListNode next = null;
        ListNode pre = null;
        while (cur.next != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        cur.next = pre;
        return cur;
    }

    /**
     * @description: Ones and Zeroes
     * ou are given an array of binary strings strs and two integers m and n.
     * <p>
     * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
     * <p>
     * A set x is a subset of a set y if all elements of x are also elements of y.
     * @return: 最大的子集 包含m个0，n个1
     * @author: kami
     * @备注： 没做
     * @date: 2021/4/12 18:02
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int zeroCnt = 0;
        int oneCnt = 0;
        int size = 0;
        List<String> strings = Arrays.stream(strs).sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        for (String str : strings) {

        }
        return size;
    }

    /**
     * @description: Beautiful Arrangement II
     * Given two integers n and k, construct a list answer that contains n different positive integers ranging
     * from 1 to n and obeys the following requirement:
     * Suppose this list is answer = [a1, a2, a3, ... , an], then the list [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... ,
     * |an-1 - an|] has exactly k distinct integers.
     * Return the list answer. If there multiple valid answers, return any of them.
     * 最多有n-1个不同的数字，左边数字增加，右边数字减少
     * i++ j-- i++ j--  i++ i++ i++ ...
     * out: 1   9   2   8    3   4   5   6   7
     * dif:   8   7   6   5    1   1   1   1
     * @return: 满足条件的数组
     * @author: kami
     * @备注： 最多有n-1个不同的值
     * @date: 2021/4/15 11:25
     */
    public int[] constructArray(int n, int k) {
        int[] result = new int[n];
        int left = 1, right = n;
        for (int i = 0; i < n; i++) {
            result[i] = k % 2 != 0 ? left++ : right--;
            if (k > 1) {
                k--;
            }
        }
        return result;
    }

    class NestedIterator implements Iterator<Integer> {

        Deque<NestedInteger> stack = new ArrayDeque<>();

        public NestedIterator(List<NestedInteger> nestedList) {
            prepareStack(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty() && !stack.peek().isInteger()) {
                List<NestedInteger> list = stack.pop().getList();
                prepareStack(list);
            }
            return !stack.isEmpty();
        }

        private void prepareStack(List<NestedInteger> nestedList) {
            for (int i = nestedList.size() - 1; i >= 0; i--) {
                stack.push(nestedList.get(i));
            }
        }
    }

    /**
     * @description: Fibonacci Number
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence,
     * such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
     * <p>
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     * @return: 斐波那契数列之和
     * @author: kami
     * @备注： 动态规划
     * @date: 2021/4/16 10:29
     */
    public int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * @description: Partition List
     * Given the head of a linked list and a value x, partition it such that all nodes less than x come
     * before nodes greater than or equal to x.
     * <p>
     * You should preserve the original relative order of the nodes in each of the two partitions.
     * @return: 排序后的头结点
     * @author: kami
     * @备注： 快速排序思想
     * @date: 2021/4/16 10:45
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode smallerHead = new ListNode(0), biggerHead = new ListNode(0);
        ListNode smaller = smallerHead, bigger = biggerHead;
        while (head != null) {
            if (head.val < x) {
                smaller = smaller.next = head;
            } else {
                bigger = bigger.next = head;
            }
            head = head.next;
        }
        // no need for extra check because of fake heads
        smaller.next = biggerHead.next; // join bigger after smaller
        bigger.next = null; // cut off anything after bigger
        return smallerHead.next;
    }

    /**
     * @description: Brick Wall
     * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have
     * the same height but different width. You want to draw a vertical line from the top to the bottom and cross
     * the least bricks.
     * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of
     * each brick in this row from left to right.
     * If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find
     * out how to draw the line to cross the least bricks and return the number of crossed bricks.
     * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will
     * obviously cross no bricks.
     * @return: 穿过最少的砖的数量
     * @author: kami
     * @备注：
     * @date: 2021/4/23 8:47
     */
    public int leastBricks(List<List<Integer>> wall) {
        int size = wall.size();
        // key:列下标 value:此列上砖与砖的连接数
        Map<Integer, Integer> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            List<Integer> bricks = wall.get(i);
            int len = bricks.size() - 1;
            int preValue = 0;
            for (int j = 0; j < len; j++) {
                int curBrick = bricks.get(j);
                preValue = preValue + curBrick;
                map.put(preValue, map.getOrDefault(preValue, 0) + 1);
            }
        }
        int maxBrick = 0;
        for (Integer val : map.values()) {
            maxBrick = Math.max(maxBrick, val);
        }

        return size - maxBrick;
    }

    /**
     * @description: Count Binary Substrings
     * Give a string s, count the number of non-empty (contiguous) substrings that have the same number of 0's and 1's,
     * and all the 0's and all the 1's in these substrings are grouped consecutively.
     * Substrings that occur multiple times are counted the number of times they occur.
     * @return: 连续子串中0 1数量相等的数量
     * @author: kami
     * @备注： First, I count the number of 1 or 0 grouped consecutively.
     * For example "0110001111" will be [1, 2, 3, 4]
     * Second, for any possible substrings with 1 and 0 grouped consecutively,
     * the number of valid substring will be the minimum number of 0 and 1.
     * For example "0001111", will be min(3, 4) = 3, ("01", "0011", "000111")
     * @date: 2021/4/24 10:00
     */
    public int countBinarySubstrings(String s) {
        int cur = 1, pre = 0, res = 0;
        for (int i = 1, len = s.length(); i < len; i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                res += Math.min(cur, pre);
                pre = cur;
                cur = 1;
            }
        }
        return res + Math.min(cur, pre);
    }

    /**
     * @description: Critical Connections in a Network
     * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections
     * forming a network where connections[i] = [a, b] represents a connection between servers a and b.
     * Any server can reach any other server directly or indirectly through the network.
     * <p>
     * A critical connection is a connection that, if removed, will make some server unable to reach some
     * other server.
     * <p>
     * Return all critical connections in the network in any order.
     * @return: 断桥集合
     * @author: kami
     * @关键词： 图论，深度优先遍历 回溯
     * @date: 2021/4/25 12:37
     */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // 邻点集合
        Map<Integer, Set<Integer>> map = buildNeighbor(n, connections);
        // 存放每个节点的id是什么，下标是i,对应节点i.因为节点范围0到n-1
        int[] ids = new int[n];
        Arrays.fill(ids, -1);

        List<List<Integer>> res = new LinkedList<>();
        dfsNeighborNode(0, 0, -1, ids, map, res);
        return res;
    }

    private int dfsNeighborNode(int curNode, int curNodeExpectId, int parent, int[] ids, Map<Integer, Set<Integer>> map, List<List<Integer>> res) {
        ids[curNode] = curNodeExpectId;
        Set<Integer> set = map.get(curNode);
        for (Integer neighbor : set) {
            if (neighbor == parent) {
                continue;
            } else if (ids[neighbor] == -1) {
                ids[curNode] = Math.min(ids[curNode], dfsNeighborNode(neighbor, curNodeExpectId + 1, curNode, ids, map, res));
            } else {
                ids[curNode] = Math.min(ids[curNode], ids[neighbor]);
            }
        }
        if (ids[curNode] == curNodeExpectId && curNode != 0) {
            res.add(Arrays.asList(parent, curNode));
        }
        return ids[curNode];
    }

    private Map<Integer, Set<Integer>> buildNeighbor(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> map = new HashMap<>(n);
        for (List<Integer> edge : connections) {
            int n1 = edge.get(0);
            int n2 = edge.get(1);

            Set<Integer> n1Set = map.getOrDefault(n1, new HashSet<>());
            Set<Integer> n2Set = map.getOrDefault(n2, new HashSet<>());

            n1Set.add(n2);
            n2Set.add(n1);

            map.put(n1, n1Set);
            map.put(n2, n2Set);
        }
        return map;
    }

    /**
     * @description: Rotate Image
     * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
     * <p>
     * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
     * DO NOT allocate another 2D matrix and do the rotation.
     * /*
     * * clockwise rotate
     * * first reverse up to down, then swap the symmetry
     * * 1 2 3     7 8 9     7 4 1
     * * 4 5 6  => 4 5 6  => 8 5 2
     * * 7 8 9     1 2 3     9 6 3
     * @return: 直接修改原数组，顺时针旋转90度
     * @author: kami
     * @关键词： 上下翻转，对称翻转实现顺时针；左右翻转，对称翻转实现逆时针
     * @date: 2021/4/25 20:28
     */
    public void rotate(int[][] matrix) {
        // 上下翻转
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0, end = row / 2; i < end; i++) {
            for (int j = 0; j < col; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[row - i - 1][j];
                matrix[row - i - 1][j] = temp;
            }
        }
        // 对称翻转
        for (int i = 0; i < row; i++) {
            for (int j = i + 1; j < col; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * @description: Furthest Building You Can Reach
     * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
     * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
     * While moving from building i to building i+1 (0-indexed),
     * If the current building's height is greater than or equal to the next building's height, you do not need
     * a ladder or bricks.
     * If the current building's height is less than the next building's height, you can either use one ladder or
     * (h[i+1] - h[i]) bricks.
     * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
     * Heap heap store k height difference that we need to use ladders.
     * Each move, if the height difference d > 0,
     * we push d into the priority queue pq.
     * If the size of queue exceed ladders,
     * it means we have to use bricks for one move.
     * Then we pop out the smallest difference, and reduce bricks.
     * If bricks < 0, we can't make this move, then we return current index i.
     * If we can reach the last building, we return A.length - 1.
     * @return: 使用砖或者梯子能到达的最远的地方
     * @author: kami
     * @关键词： 小顶堆
     * @date: 2021/4/26 22:14
     */
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < heights.length - 1; i++) {
            int d = heights[i + 1] - heights[i];
            if (d > 0) {
                pq.add(d);
            }
            if (pq.size() > ladders) {
                bricks -= pq.poll();
            }
            if (bricks < 0) {
                return i;
            }
        }
        return heights.length - 1;
    }

    /**
     * @description: Power of Three
     * Given an integer n, return true if it is a power of three. Otherwise, return false.
     * An integer n is a power of three, if there exists an integer x such that n == 3x.
     * @return: 是否是3的幂次
     * @author: kami
     * @关键词：
     * @date: 2021/4/28 12:36
     */
    public boolean isPowerOfThree(int n) {
        if (n < 1) {
            return false;
        }
        while (n > 1) {
            if (n % 3 != 0) {
                return false;
            }
            n /= 3;
        }
        return true;
    }

    /**
     * @description: Unique Paths II
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     * <p>
     * The robot can only move either down or right at any point in time. The robot is trying to reach
     * the bottom-right corner of the grid (marked 'Finish' in the diagram below).
     * <p>
     * Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * <p>
     * An obstacle and space is marked as 1 and 0 respectively in the grid.
     * @return: 路径的个数
     * @author: kami
     * @关键词： 动态规划，依次计算到当前点的路径个数
     * @date: 2021/4/28 15:20
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;//行数
        int n = obstacleGrid[0].length;//列数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //如果遇到障碍，就把障碍清除，这样最终的结果加和0值就相当于没有加
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    //左上角起始点，设置值为1
                    if (i == 0 && j == 0) {
                        obstacleGrid[i][j] = 1;
                    }
                    //如果是第一行，后值设置与前值相同
                    else if (i == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                    }
                    //如果是第一列，后值设置与前值相同
                    else if (j == 0) {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                    }
                    //如果其他地方的值，则把其同行前值 与 同列前值 相加 赋给此处的值
                    else {
                        obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
                    }
                }
            }
        }
        //返回最后一个位置的值，其值 为 之前特定值的加和
        return obstacleGrid[m - 1][n - 1];
    }

    /**
     * @description: 328. Odd Even Linked List
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes
     * with even indices, and return the reordered list.
     * The first node is considered odd, and the second node is even, and so on.
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     * @return: 重排序的链表
     * @author: kami
     * @关键词：
     * @date: 2021/4/30 20:06
     */
    public ListNode oddEvenList(ListNode head) {
        if (head != null) {

            ListNode odd = head, even = head.next, evenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = even.next.next;
                odd = odd.next;
                even = even.next;
            }
            odd.next = evenHead;
        }
        return head;
    }

    /**
     * @description: Prefix and Suffix Search
     * Design a special dictionary which has some words and allows you to search the words in it by a prefix
     * and a suffix.
     * Implement the WordFilter class:
     * WordFilter(string[] words) Initializes the object with the words in the dictionary.
     * f(string prefix, string suffix) Returns the index of the word in the dictionary which has the prefix prefix
     * and the suffix suffix. If there is more than one valid index, return the largest of them. If there is no
     * such word in the dictionary, return -1
     * @author: kami
     * @关键词： 最左前缀
     * @date: 2021/5/2 17:54
     */
    class WordFilter {

        HashMap<String, Integer> map = new HashMap<>();

        public WordFilter(String[] words) {
            for (int w = 0; w < words.length; w++) {
                for (int i = 0; i <= 10 && i <= words[w].length(); i++) {
                    for (int j = 0; j <= 10 && j <= words[w].length(); j++) {
                        map.put(words[w].substring(0, i) + "#" + words[w].substring(words[w].length() - j), w);
                    }
                }
            }
        }

        public int f(String prefix, String suffix) {
            return map.getOrDefault(prefix + "#" + suffix, -1);
        }
    }
    /**
     * @description: Course Schedule III
     * There are n different online courses numbered from 1 to n. You are given an array courses where
     * courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for
     * durationi days and must be finished before or on lastDayi.
     * You will start on the 1st day and you cannot take two or more courses simultaneously.
     * Return the maximum number of courses that you can take.
     *
     * Approach:
     * Sort courses by the end date, this way, when we're iterating through the courses,
     * we can switch out any previous course with the current one without worrying about end date.
     * Next, we iterate through each course, if we have enough days, we'll add it to our priority queue.
     * If we don't have enough days, then we can either
     * 2.1 ignore this course OR
     * 2.2 We can replace this course with the longest course we added earlier.
     * @return: 能上最多多少门课
     * @author: kami
     * @关键词： 贪心算法
     * @date: 2021/5/2 17:57
     */
    public int scheduleCourse(int[][] courses) {
        // Sort the courses by their deadlines (Greedy! We have to deal with courses with early deadlines first)
        Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
        // 大顶堆
        PriorityQueue<Integer> pq=new PriorityQueue<>((a,b)->b-a);
        int time=0;
        for (int[] c:courses)
        {
            time+=c[0]; // add current course to a priority queue
            pq.add(c[0]);
            if (time>c[1]) {
                // If time exceeds, drop the previous course which costs the most time. (That must be the best choice!)
                time-=pq.poll();
            }
        }
        return pq.size();
    }
    /**
     * @description: Running Sum of 1d Array
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     *
     * Return the running sum of nums.
     * @return: 加和的数组
     * @author: kami
     * @关键词： 动态规划
     * @date: 2021/5/4 14:45
     */
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i-1] + nums[i];
        }
        return nums;
    }
    /**
     * @description: Non-decreasing Array
     * Given an array nums with n integers, your task is to check if it could become non-decreasing by
     * modifying at most one element.
     * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based)
     * such that (0 <= i <= n - 2).
     * This problem is like a greedy problem. When you find nums[i-1] > nums[i] for some i, you will prefer to
     * change nums[i-1]'s value, since a larger nums[i] will give you more risks that you get inversion errors after
     * position i. But, if you also find nums[i-2] > nums[i], then you have to change nums[i]'s value instead,
     * or else you need to change both of nums[i-2]'s and nums[i-1]'s values.
     * @return: 是否值修改一个数字就能使数组变为递增的
     * @author: kami
     * @关键词： 贪心思想
     * @date: 2021/5/5 7:56
     */
    public boolean checkPossibility(int[] nums) {
        int cnt = 0;                                                                    //the number of changes
        for(int i = 1; i < nums.length && cnt<=1 ; i++){
            // 如果遇到非递增
            if(nums[i-1] > nums[i]){
                cnt++;
                if(i-2<0 || nums[i-2] <= nums[i]) {
                    nums[i-1] = nums[i];                    //modify nums[i-1] of a priority
                } else {
                    nums[i] = nums[i-1];                    //have to modify nums[i]
                }
            }
        }
        return cnt<=1;
    }
    /**
     * @description: 55Jump Game II
     * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Your goal is to reach the last index in the minimum number of jumps.
     * You can assume that you can always reach the last index.
     * @return: 跳到末尾的步数
     * @author: kami
     * @关键词： 第一步有一个范围框，第二步有一个范围框，一直向后推范围框直到范围框的最远边界到达末尾
     * @date: 2021/5/6 8:59
     */
    public int jump(int[] nums) {
        // 当前步数能跳到的最大位置，最大位置的下标，步数
        int newMax = 0,maxI=0,step=0;
        for (int i = 0; i < nums.length-1; i++) {
            //
            newMax = Math.max(newMax,i+nums[i]);
            if (i == maxI){
                step++;
                maxI = newMax;
                if (maxI >= nums.length-1){
                    break;
                }
            }
        }
        return step;
    }
    /**
     * @description: Convert Sorted List to Binary Search Tree
     * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a
     * height balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the
     * two subtrees of every node never differ by more than 1.
     * @return: 构建一个平衡二叉树
     * @author: kami
     * @关键词： 递归构建二叉树，每次从链表的中间节点构建
     * @date: 2021/5/6 21:22
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null) {
            return null;
        }
        return toBST(head,null);

    }
    public TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail) {
            return null;
        }

        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode thead = new TreeNode(slow.val);
        thead.left = toBST(head,slow);
        thead.right = toBST(slow.next,tail);
        return thead;
    }
    /**
     * @description: Delete Operation for Two Strings
     * Given two strings word1 and word2, return the minimum number of steps
     * required to make word1 and word2 the same.
     * In one step, you can delete exactly one character in either string.
     * @return: 删除最小步骤
     * @author: kami
     * @关键词： 最长公共子串，动态规划
     * @date: 2021/5/8 10:29
     */
    public int minDistance(String word1, String word2) {
        // dp[i][j] 表示word1中i串 和 word2中j串 的最大公共子串的长度
        int dp[][] = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i <= word1.length(); i++) {
            for(int j = 0; j <= word2.length(); j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = (word1.charAt(i-1) == word2.charAt(j-1)) ? dp[i-1][j-1] + 1
                            : Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        int val =  dp[word1.length()][word2.length()];
        return word1.length() - val + word2.length() - val;

    }
    /**
     * @description: Super Palindromes
     * Let's say a positive integer is a super-palindrome if it is a palindrome, and it is also
     * the square of a palindrome.
     * Given two positive integers left and right represented as strings, return the number of super-palindromes
     * integers in the inclusive range [left, right].
     * @return: 超级回文数字的个数
     * @author: kami
     * @关键词： 构造回文数字而不是遍历所有数字
     * @date: 2021/5/8 17:38
     */
    public int superpalindromesInRange(String L, String R) {
        List<Long> palindromes = new ArrayList<>();
        long low = Long.parseLong(L);
        long high = Long.parseLong(R);
        int res = 0;
        // 添加1-9数字
        for (long i = 1; i <= 9; i++) {
            palindromes.add(i);
        }
        for (long i = 1; i < 10000; i++) {
            // left: 15 right:51
            String left = Long.toString(i);
            String right = new StringBuilder(left).reverse().toString();
            // 1551
            palindromes.add(Long.parseLong(left + right));
            // 15051,15151,15251,15351...15951
            for (long d = 0; d < 10; d++) {
                palindromes.add(Long.parseLong(left + d + right));
            }
        }
        for (long palindrome : palindromes) {
            long square = palindrome * palindrome;
            if (!isPalindrome(Long.toString(square))) {
                continue;
            }
            if (low <= square && square <= high) {
                res++;
            }
        }
        return res;
    }

    private boolean isPalindrome(String str) {
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    /**
     * @description: Construct Target Array With Multiple Sums
     * Given an array of integers target. From a starting array, A consisting of all 1's,
     * you may perform the following procedure :
     *
     * let x be the sum of all elements currently in your array.
     * choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
     * You may repeat this procedure as many times as needed.
     * Return True if it is possible to construct the target array from A otherwise return False.
     * @return: 能否按照规则将【1,1，...，】变成 target数组
     * @author: kami
     * @关键词： 最大的数字一定是在最后一步填充的
     * @date: 2021/5/10 16:37
     */
    public static boolean isPossible(int[] target) {
        if (target.length == 1 && target[0] != 1) {
            return false;
        }

        long sum = 0;			// 和可能越界
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int num : target) {
            sum += num;
            pq.offer(num);
        }

        while (pq.peek() > 1) {
            int top = pq.poll();
            sum -= top;
            if (sum == 1) {
                return true;			// 如果剩余之和为1，那么一定能将top还原为1，如：[4,1]
            }
            if (top <= sum || top % sum == 0) {
                return false;		// 特殊情况：[2,2]
            }
            top %= sum;			// 还原top
            sum += top;
            pq.offer(top);
        }

        return true;
    }
    /**
     * @description: Count Primes
     * Count the number of prime numbers less than a non-negative number, n.
     * @return: 素数的数量
     * @author: kami
     * @关键词： 计算合数的数量，用N-合数数量=素数数量
     * @date: 2021/5/11 9:37
     */
    public static int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        // Loop's ending condition is i * i < n instead of i < sqrt(n)
        // to avoid repeatedly calling an expensive function sqrt().
        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        return count;
    }
    /**
     * @description: Maximum Points You Can Obtain from Cards
     * There are several cards arranged in a row, and each card has an associated number of points
     * The points are given in the integer array cardPoints.
     * In one step, you can take one card from the beginning or from the end of the row. You have
     * to take exactly k cards.
     * Your score is the sum of the points of the cards you have taken
     * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
     * @return: 获取最高分是多少
     * @author: kami
     * @关键词：
     * @date: 2021/5/11 21:01
     */
    public static int maxScore(int[] cardPoints, int k) {
        int size = cardPoints.length;
        int[] front = new int[size];
        front[0] = cardPoints[0];
        int[] back = new int[size];
        back[size-1] = cardPoints[size-1];
        for (int i = 1; i < size; i++) {
            front[i] = front[i-1]+cardPoints[i];
            back[size-i-1] = back[size-i] + cardPoints[size-i-1];
        }
        int max = 0;
        for (int i = 0; i <= k; i++) {
            if (i == 0){
                max = Math.max(max,back[size-k]);
            }else if (i == k){
                max = Math.max(max,front[k-1]);
            }else {
                max = Math.max(max,front[i-1]+back[size - (k-i)]);
            }
        }
        return max;

    }

    class NumMatrix {
        int[][] matrix = null;
        public NumMatrix(int[][] matrix) {
            this.matrix=matrix;
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int rowMax = matrix.length-1;
            int colMax = matrix[0].length-1;
            int sum = 0;
            for (int i = Math.max(0,row1),rowEnd=Math.min(row2,rowMax); i <= rowEnd; i++) {
                for (int j = Math.max(0,col1),colEnd=Math.min(col2,colMax); j <= colEnd ; j++) {
                    sum += matrix[i][j];
                }
            }
            return sum;
        }
    }
    /**
     * @description: Ambiguous Coordinates
     * We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)".  Then, we removed all commas, decimal points,
     * and spaces, and ended up with the string s.  Return a list of strings representing all possibilities for what our original coordinates could have been.
     * Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0",
     * "0.00", "1.0", "001", "00.01", or any other number that can be represented with less digits.  Also, a decimal
     * point within a number never occurs without at least one digit occuring before it, so we never started with
     * numbers like ".1".
     * The final answer list can be returned in any order.  Also note that all coordinates in the final answer have
     * exactly one space between them (occurring after the comma.)
     * @return: TODO
     * @author: kami
     * @关键词：TODO
     * @date: 2021/5/13 22:09
     */
    public List<String> ambiguousCoordinates(String s) {
        return null;
    }

    public static void main(String[] args) {
        int[] nu = {1,2,3,4,5,6,1};

        System.out.println(maxScore(nu,3));
    }
}
