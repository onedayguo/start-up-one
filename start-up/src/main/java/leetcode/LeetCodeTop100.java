package leetcode;

import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @Description: LeetCode最受欢迎100题
 * @Auther: kami
 * @Date: 2020/4/12 16:08
 * @Version: 1.0.0
 */
public class LeetCodeTop100 {
    /**
     * @description: 139. Word Break 中等难度
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * Note:
     * The same word in the dictionary may be reused multiple times in the segmentation.
     * You may assume the dictionary does not contain duplicate words.
     * Input: s = "leetcode", wordDict = ["leet", "code"]
     * Output: true
     * Explanation: Return true because "leetcode" can be segmented as "leet code".
     * @return:
     * @auther: kami
     * @date: 2020/4/12 16:09
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        //此方法时间超过限制
        int size = wordDict.size();
        return helperWordBreak(s, wordDict, size);
    }

    private static boolean helperWordBreak(String newStr, List<String> wordDict, int size) {
        if (newStr == null || newStr.length() == 0) return true;
        for (int i = 0; i < size; i++) {
            boolean flag = newStr.startsWith(wordDict.get(i));
            if (flag) {
                int iSize = wordDict.get(i).length();
                String nextStr = newStr.substring(iSize);
                if (helperWordBreak(nextStr, wordDict, size)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @description: 动态规划
     * @return:
     * @auther: kami
     * @date: 2020/4/12 16:42
     */
    public boolean wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0) return false;

        int n = s.length();

        // dp[i] represents whether s[0...i] can be formed by dict
        boolean[] dp = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                String sub = s.substring(j, i + 1);

                if (dict.contains(sub) && (j == 0 || dp[j - 1])) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n - 1];
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * @description: 98. Validate Binary Search Tree中等难度
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     * Assume a BST is defined as follows:
     * The left subtree of a node contains only nodes with keys less than the node's key.
     * The right subtree of a node contains only nodes with keys greater than the node's key.
     * Both the left and right subtrees must also be binary search trees.
     * @return:
     * @auther: kami
     * @date: 2020/4/12 23:40
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    /**
     * @description: 96. Unique Binary Search Trees 动态规划，中等难度
     * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
     * The problem can be solved in a dynamic programming way. I’ll explain the intuition and formulas in the following.
     * Given a sequence 1…n, to construct a Binary Search Tree (BST) out of the sequence, we could enumerate each number
     * i in the sequence, and use the number as the root, naturally, the subsequence 1…(i-1) on its left side would lay on
     * the left branch of the root, and similarly the right subsequence (i+1)…n lay on the right branch of the root. We then
     * can construct the subtree from the subsequence recursively. Through the above approach, we could ensure that the BST
     * that we construct are all unique, since they have unique roots.
     * The problem is to calculate the number of unique BST. To do so, we need to define two functions:
     * G(n): the number of unique BST for a sequence of length n.
     * F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the root of BST, and the sequence ranges from 1 to n.
     * As one can see, G(n) is the actual function we need to calculate in order to solve the problem. And G(n) can be
     * derived from F(i, n), which at the end, would recursively refer to G(n).
     * First of all, given the above definitions, we can see that the total number of unique BST G(n), is the sum of BST F(i) using each number i as a root.
     * i.e.
     * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
     * Particularly, the bottom cases, there is only one combination to construct a BST out of a sequence of length 1
     * (only a root) or 0 (empty tree).
     * i.e.
     * G(0)=1, G(1)=1.
     * Given a sequence 1…n, we pick a number i out of the sequence as the root, then the number of unique BST with the
     * specified root F(i), is the cartesian product of the number of BST for its left and right subtrees. For example,
     * F(3, 7): the number of unique BST tree with number 3 as its root. To construct an unique BST out of the entire
     * sequence [1, 2, 3, 4, 5, 6, 7] with 3 as the root, which is to say, we need to construct an unique BST out of
     * its left subsequence [1, 2] and another BST out of the right subsequence [4, 5, 6, 7], and then combine them
     * together (i.e. cartesian product). The tricky part is that we could consider the number of unique BST out of
     * sequence [1,2] as G(2), and the number of of unique BST out of sequence [4, 5, 6, 7] as G(4). Therefore, F(3,7) = G(2) * G(4).
     * i.e.
     * F(i, n) = G(i-1) * G(n-i)	1 <= i <= n
     * Combining the above two formulas, we obtain the recursive formula for G(n). i.e.
     * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
     * In terms of calculation, we need to start with the lower number, since the value of G(n) depends on the values of G(0) … G(n-1).
     * With the above explanation and formulas, here is the implementation in Java.
     * @return:
     * @auther: kami
     * @date: 2020/4/14 14:29
     */
    public int numTrees(int n) {
        int[] sum = new int[n + 1];
        sum[0] = 1;
        sum[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                sum[i] += (sum[j - 1] * sum[i - j]);
            }
        }
        return sum[n];
    }

    /**
     * @description: 347. Top K Frequent Elements
     * Given a non-empty array of integers, return the k most frequent elements.
     * @return: 出现次数最多的k个数的数组
     * @auther: kami
     * @date: 2020/4/17 7:34
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

        // keep k top frequent elements in the heap
        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList<>();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }

    /**
     * @description: 494. Target Sum
     * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -.
     * For each integer, you should choose one from + and - as its new symbol.
     * Find out how many ways to assign symbols to make sum of integers equal to target S.
     * @return: 运算后得到目标值的 方式种类
     * @auther: kami
     * @date: 2020/4/18 23:06
     */
    int count = 0;

    public int findTargetSumWays(int[] nums, int S) {
//        calculateBruteForce(nums,0,0,S);
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < S || (S + sum) % 2 > 0 ? 0 : subsetSum(nums, (S + sum) >>> 1);

    }

    //https://blog.csdn.net/hit0803107/article/details/54894227
    public int findTargetSumWays1(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || (sum + S) % 2 != 0) {
            return 0;
        }
        sum = (sum + S) / 2;
        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = sum; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[sum];
    }

    /**
     * @description: 494. Target Sum,暴力求解法
     * @return:
     * @auther: kami
     * @date: 2020/4/19 8:11
     */
    private void calculateBruteForce(int[] nums, int index, int sum, int S) {
        if (index == nums.length && sum == S) count++;
        else {
            calculateBruteForce(nums, index + 1, sum + nums[index], S);
            calculateBruteForce(nums, index + 1, sum - nums[index], S);
        }
    }

    /**
     * @description: 494. Target Sum动态规划
     * the recursive solution is very slow, because its runtime is exponential
     * The original problem statement is equivalent to:
     * Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target
     * Let P be the positive subset and N be the negative subset
     * For example:
     * Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
     * Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]
     * Then let's see how this can be converted to a subset sum problem:
     * sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     * 2 * sum(P) = target + sum(nums)
     * So the original problem has been converted to a subset sum problem as follows:
     * Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
     * Note that the above formula has proved that target + sum(nums) must be even
     * We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the
     * suggestion)
     * For detailed explanation on how to solve subset sum problem, you may refer to Partition Equal Subset Sum
     * @return:
     * @auther: kami
     * @date: 2020/4/19 9:38
     */
    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n];
        return dp[s];
    }

    /**
     * @description: 416. Partition Equal Subset Sum
     * Given a non-empty array containing only positive integers, find if the array can be partitioned into two
     * subsets such that the sum of elements in both subsets is equal.
     * Note:
     * Each of the array element will not exceed 100.
     * The array size will not exceed 200.
     * <p>
     * This problem is essentially let us to find whether there are several numbers in a set which are able to sum to a
     * specific value (in this problem, the value is sum/2).
     * Actually, this is a 0/1 knapsack problem, for each number, we can pick it or not. Let us assume dp[i][j] means
     * whether the specific sum j can be gotten from the first i numbers. If we can pick such a series of numbers from
     * 0-i whose sum is j, dp[i][j] is true, otherwise it is false.
     * Base case: dp[0][0] is true; (zero number consists of sum 0 is true)
     * Transition function: For each number, if we don't pick it, dp[i][j] = dp[i-1][j], which means if the first i-1
     * elements has made it to j, dp[i][j] would also make it to j (we can just ignore nums[i]). If we pick nums[i].
     * dp[i][j] = dp[i-1][j-nums[i]], which represents that j is composed of the current value nums[i] and the remaining
     * composed of other previous numbers. Thus, the transition function is dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]]
     * @return:
     * @auther: kami
     * @date: 2020/4/19 14:02
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i < n + 1; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < sum + 1; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] = (dp[i][j] || dp[i - 1][j - nums[i - 1]]);
                }
            }
        }
        return dp[n][sum];
    }

    /**
     * @description: But can we optimize it? It seems that we cannot optimize it in time. But we can optimize in space.
     * We currently use two dimensional array to solve it, but we can only use one dimensional array.
     * @return:
     * @auther: kami
     * @date: 2020/4/19 16:10
     */
    public boolean canPartition1(int[] nums) {
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) {
            return false;
        }
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = sum; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }

        return dp[sum];
    }

    /**
     * @description: 101. Symmetric Tree
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * But the following [1,2,2,null,3,null,3] is not:
     * 1
     * / \
     * 2   2
     * \   \
     * 3    3
     * @return:
     * @auther: kami
     * @date: 2020/4/19 16:16
     */
    public boolean isSymmetric(TreeNode root) {
//        return isMirrorRecursive(root,root);
        return isMirrorIteration(root);
    }

    /**
     * @discription 镜像判断，递归性判断 Recursive
     * @date 2020/4/19 18:57
     **/
    private static boolean isMirrorRecursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val
                && isMirrorRecursive(left.left, right.right)
                && isMirrorRecursive(left.right, right.left);
    }

    /**
     * @discription 镜像判断，迭代性判断
     * Instead of recursion, we can also use iteration with the aid of a queue. Each two consecutive nodes in the queue
     * should be equal, and their subtrees a mirror of each other. Initially, the queue contains root and root. Then the
     * algorithm works similarly to BFS, with some key differences. Each time, two nodes are extracted and their values
     * compared. Then, the right and left children of the two nodes are inserted in the queue in opposite order. The
     * algorithm is done when either the queue is empty, or we detect that the tree is not symmetric (i.e. we pull out
     * two consecutive nodes from the queue that are unequal).
     * @date 2020/4/19 18:59
     **/
    private static boolean isMirrorIteration(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    /**
     * @description: 560. Subarray Sum Equals K
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose
     * sum equals to k.
     * @return:
     * @auther: kami
     * @date: 2020/4/19 19:08
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int tempSum = nums[i];
            if (tempSum == k) {
                count++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                tempSum += nums[j];
                if (tempSum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @description: 148. Sort List
     * Sort a linked list in O(n log n) time using constant space complexity.
     * Example 1:
     * Input: 4->2->1->3
     * Output: 1->2->3->4
     * Example 2:
     * Input: -1->5->3->4->0
     * Output: -1->0->3->4->5
     * @return:
     * @auther: kami
     * @date: 2020/4/19 20:20
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, fast = head, pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        return merge(sortList(head), sortList(slow));
    }

    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }

    /**
     * @description: 239. Sliding Window Maximum
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
     * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     * Return the max sliding window.
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     * <p>
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     * 1 [3  -1  -3] 5  3  6  7       3
     * 1  3 [-1  -3  5] 3  6  7       5
     * 1  3  -1 [-3  5  3] 6  7       5
     * 1  3  -1  -3 [5  3  6] 7       6
     * 1  3  -1  -3  5 [3  6  7]      7
     * Follow up:
     * Could you solve it in linear time?
     * @return: 滑动窗口最大值组成的列表
     * @auther: kami
     * @date: 2020/4/22 6:50
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        return maxSlidingWindowBruteForce(nums, k);
    }

    /**
     * @description: 239. Sliding Window Maximum 暴力破解
     * @return:
     * @auther: kami
     * @date: 2020/4/22 20:06
     */
    private int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        int resLen = nums.length - k + 1;
        int[] resArr = new int[resLen];
        for (int i = 0; i < resLen; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < k; j++) {
                max = Math.max(max, nums[i + j]);
            }
            resArr[i] = max;
        }
        return resArr;
    }

    /**
     * @description: 队列
     * @return:
     * @auther: kami
     * @date: 2020/4/22 21:31
     */
    public int[] maxSlidingWindowDeque(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }
        int n = a.length;
        int[] resArr = new int[n - k + 1];
        int resIndex = 0;
        // store index
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!q.isEmpty() && q.peek() < i - k + 1) {
                q.poll();
            }
            // remove smaller numbers in k range as they are useless
            while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
                q.pollLast();
            }
            // q contains index... r contains content
            q.offer(i);
            if (i >= k - 1) {
                resArr[resIndex++] = a[q.peek()];
            }
        }
        return resArr;
    }

    /**
     * @description: 581. Shortest Unsorted Continuous Subarray
     * Given an integer array, you need to find one continuous subarray that if you only sort this subarray
     * in ascending order, then the whole array will be sorted in ascending order, too.
     * You need to find the shortest such subarray and output its length.
     * @return:
     * @auther: kami
     * @date: 2020/5/6 21:19
     */
    public static int findUnsortedSubarray(int[] nums) {
        int length = nums.length;
        int lowIndex = -1;
        int highIndex = -1;

        int[] copy = Arrays.copyOf(nums, length);
        Arrays.sort(copy);

        for (int i = 0; i < length; i++) {
            if (nums[i] != copy[i]) {
                lowIndex = i;
                break;
            }
        }
        for (int i = length - 1; i > 0; i--) {
            if (nums[i] != copy[i]) {
                highIndex = i;
                break;
            }
        }
        int diff = highIndex - lowIndex;
        return diff == 0 ? 0 : diff + 1;
    }

    /**
     * @description: 297. Serialize and Deserialize Binary Tree
     * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
     * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
     * the same or another computer environment.
     * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
     * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be
     * serialized to a string and this string can be deserialized to the original tree structure.
     * @return:
     * @auther: kami
     * @date: 2020/5/6 21:57
     */
    public class Codec {
        private static final String spliter = ",";
        private static final String NN = "X";

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            buildString(root, sb);
            return sb.toString();
        }

        private void buildString(TreeNode node, StringBuilder sb) {
            if (node == null) {
                sb.append(NN).append(spliter);
            } else {
                sb.append(node.val).append(spliter);
                buildString(node.left, sb);
                buildString(node.right, sb);
            }
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            Deque<String> nodes = new LinkedList<>(Arrays.asList(data.split(spliter)));
            return buildTree(nodes);
        }

        private TreeNode buildTree(Deque<String> nodes) {
            String val = nodes.remove();
            if (val.equals(NN)) return null;
            else {
                TreeNode node = new TreeNode(Integer.parseInt(val));
                node.left = buildTree(nodes);
                node.right = buildTree(nodes);
                return node;
            }
        }
    }

    /**
     * @description: 617. Merge Two Binary Trees
     * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees
     * are overlapped while the others are not.
     * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values
     * up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.
     * @return: 合并后的树节点
     * @auther: kami
     * @date: 2020/5/15 22:26
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        return helperMergeTrees(new TreeNode(), t1, t2);
    }

    private TreeNode helperMergeTrees(TreeNode curr, TreeNode node1, TreeNode node2) {
        if (node1 != null && node2 != null) {
            curr.val = node1.val + node2.val;
            TreeNode left = helperMergeTrees(new TreeNode(), node1.left, node2.left);
            curr.left = left;
            TreeNode right = helperMergeTrees(new TreeNode(), node1.right, node2.right);
            curr.right = right;
        } else if (node1 != null) {
            curr = node1;
        } else {
            curr = node2;
        }
        return curr;
    }

    /**
     * @description: 104. Maximum Depth of Binary Tree
     * Given a binary tree, find its maximum depth.
     * The maximum depth is the number of nodes along the longest path from the root node down to the
     * farthest leaf node.
     * Note: A leaf is a node with no children.
     * @return: 树的深度
     * @auther: kami
     * @date: 2020/5/15 23:24
     */
    public int maxDepth(TreeNode root) {
        // 递归
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public int maxDepthIterative(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> depthStack = new Stack<>();

        int max = 0;
        int depth = 1;
        while (node != null || nodeStack.size() > 0) {
            if (node != null) {
                nodeStack.push(node);
                depthStack.push(depth);
                node = node.left;
                depth++;
            } else {
                node = nodeStack.pop();
                depth = depthStack.pop();

                if (depth > max) {
                    max = depth;
                }

                node = node.right;
                depth++;
            }
        }

        return max;
    }

    /**
     * @description: 226. Invert Binary Tree
     * Invert a binary tree.
     * @return: 树
     * @auther: kami
     * @date: 2020/5/23 20:17
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        mirrorFlip(root, root.left, root.right);
        return root;
    }

    private void mirrorFlip(TreeNode parentNode, TreeNode leftNode, TreeNode rightNode) {
        if (parentNode == null || (leftNode == null && rightNode == null)) {
            return;
        } else if (leftNode != null && rightNode != null) {
            TreeNode leftTemp = leftNode;
            parentNode.left = parentNode.right;
            parentNode.right = leftTemp;
            mirrorFlip(parentNode.right, parentNode.right.left, parentNode.right.right);
            mirrorFlip(parentNode.left, parentNode.left.left, parentNode.left.right);
        } else if (leftNode != null) {
            parentNode.right = leftNode;
            parentNode.left = null;
            mirrorFlip(parentNode.right, parentNode.right.left, parentNode.right.right);
        } else {
            parentNode.left = rightNode;
            parentNode.right = null;
            mirrorFlip(parentNode.left, parentNode.left.left, parentNode.left.right);
        }
    }

    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * @description: 206. Reverse Linked List
     * Reverse a singly linked list.
     * @return: 反转链表
     * @auther: kami
     * @date: 2020/5/23 21:11
     */
    public ListNode reverseList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode copyHead = head;
        ListNode resHead = head;
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int size = list.size();
        int i = 1;
        while (copyHead != null) {
            copyHead.val = list.get(size - i);
            copyHead = copyHead.next;
            i++;
        }
        return resHead;
    }

    /**
     * @description: 169. Majority Element
     * Given an array of size n, find the majority element. The majority element is the element that appears more
     * than ⌊ n/2 ⌋ times.
     * You may assume that the array is non-empty and the majority element always exist in the array.
     * @return:
     * @auther: kami
     * @date: 2020/5/23 21:24
     */
    public int majorityElement(int[] nums) {
        int size = nums.length;
        int half = size >> 1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            if (map.containsKey(nums[i])) {
                int count = map.get(nums[i]) + 1;
                if (count > half) {
                    return nums[i];
                }
                map.put(nums[i], count);
            } else {
                map.put(nums[i], 1);
            }
        }
        return nums[0];
    }

    /**
     * @description: 448. Find All Numbers Disappeared in an Array
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others
     * appear once.
     * Find all the elements of [1, n] inclusive that do not appear in this array
     * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count
     * as extra space.
     * @return: 缺少的数的列表
     * @auther: kami
     * @date: 2020/5/23 21:34
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if (nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    /**
     * @description: 437. Path Sum III
     * You are given a binary tree in which each node contains an integer value.
     * Find the number of paths that sum to a given value.
     * The path does not need to start or end at the root or a leaf, but it must go downwards
     * (traveling only from parent nodes to child nodes).
     * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
     * @return: 加和为sum的路径数量
     * @author: kami
     * @date: 2020/5/31 12:10
     */
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        /**
         * @description: So the idea is similar as Two sum, using HashMap to store ( key : the prefix sum, value : how
         * many ways get to this prefix sum) , and whenever reach a node, we check if prefix sum - target exists in
         * hashmap or not, if it does, we added up the ways of prefix sum - target into res.
         * For instance : in one path we have 1,2,-1,-1,2, then the prefix sum will be: 1, 3, 2, 1, 3, let's say we want
         * to find target sum is 2, then we will have{2}, {1,2,-1}, {2,-1,-1,2} and {2}ways.
         *
         * I used global variable count, but obviously we can avoid global variable by passing the count from bottom up.
         * The time complexity is O(n). This is my first post in discuss, open to any improvement or criticism. :)
         * @return: int
         * @author: kami
         * @date: 2020/6/6 11:25
         */
        if (root == null) {
            return 0;
        }
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }

    /**
     * @description: 141. Linked List Cycle
     * Given a linked list, determine if it has a cycle in it.
     * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed)
     * in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
     * @return: 是否链内有环
     * @author: kami
     * @date: 2020/6/6 14:51
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode quick = head.next;
        while (quick != null) {
            if (quick.equals(slow)) {
                return true;
            }
            slow = slow.next;
            if (quick.next != null) {
                quick = quick.next.next;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @description: 160. Intersection of Two Linked Lists
     * Write a program to find the node at which the intersection of two singly linked lists begins.
     * For example, the following two linked lists:
     * @return: 交叉点的节点
     * @author: kami
     * @date: 2020/6/6 16:28
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /**
         * @description: I found most solutions here preprocess linkedlists to get the difference in len.
         * Actually we don't care about the "value" of difference, we just want to make sure two pointers reach the
         * intersection node at the same time.
         *
         * We can use two iterations to do that. In the first iteration, we will reset the pointer of one linkedlist to
         * the head of another linkedlist after it reaches the tail node. In the second iteration, we will move two
         * pointers until they points to the same node. Our operations in first iteration will help us counteract the
         * difference. So if two linkedlist intersects, the meeting point in second iteration must be the intersection
         * point. If the two linked lists have no intersection at all, then the meeting pointer in second iteration must
         * be the tail node of both lists, which is null
         * @return: leetcode.LeetCodeTop100.ListNode
         * @author: kami
         * @date: 2020/6/6 16:58
         */
        //boundary check
        if (headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    /**
     * @description: 234. Palindrome Linked List
     * Given a singly linked list, determine if it is a palindrome.
     * @return: 是否是回文链表
     * @author: kami
     * @date: 2020/6/6 17:10
     */
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != null) { // odd nodes: let right half smaller
            slow = slow.next;
        }
        slow = reverse(slow);
        fast = head;

        while (slow != null) {
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    /**
     * @description: 234. Palindrome Linked List 使用数组实现
     * @return: 是否是回文链表
     * @author: kami
     * @date: 2020/6/6 17:25
     */
    private boolean usingArray(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode newHead = head;
        List<Integer> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head.val);
            head = head.next;
        }
        int size = nodeList.size();
        int halfSize = size >> 1;
        for (int i = 0; i < halfSize; i++) {
            if (!nodeList.get(i).equals(nodeList.get(size - i - 1))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description: 215. Kth Largest Element in an Array
     * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
     * not the kth distinct element.
     * @return: 第K大的值
     * @author: kami
     * @date: 2020/6/6 17:32
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null) {
            return 0;
        }
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * @description: 406. Queue Reconstruction by Height
     * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers
     * (h, k), where h is the height of the person and k is the number of people in front of this person who have a
     * height greater than or equal to h. Write an algorithm to reconstruct the queue.
     * Note:
     * The number of people is less than 1,100.
     * @return:
     * @author: kami
     * @date: 2020/6/6 20:16
     */
    public static int[][] reconstructQueue(int[][] people) {
        /**
         * @description: Pick out tallest group of people and sort them in a subarray (S). Since there's no other groups
         * of people taller than them, therefore each guy's index will be just as same as his k value.
         * For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
         * E.g.
         * input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
         * subarray after step 1: [[7,0], [7,1]]
         * subarray after step 2: [[7,0], [6,1], [7,1]]
         * ...
         * @return: int[][]
         * @author: kami
         * @date: 2020/6/7 8:45
         */
        // pick up the tallest guy first
        // when insert the next tall guy, just need to insert him into kth position
        // repeat until all people are inserted into list
        Arrays.sort(people, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1]);
        List<int[]> res = new LinkedList<>();
        for (int[] cur : people) {
            res.add(cur[1], cur);
        }
        return res.toArray(new int[people.length][]);
    }

    /**
     * @description: 739. Daily Temperatures
     * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days
     * you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
     * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].
     * Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].
     * @return: int[]
     * @author: kami
     * @date: 2020/6/7 8:48
     */
    public int[] dailyTemperatures(int[] T) {
        /**
         * @description: 解法：栈，递减栈Descending Stack，新建一个长度和T相等的数组res，来记录天数。遍历数组，如果栈为空，直接入栈。
         * 如果栈不为空，且当前数字大于栈顶元素，pop出栈顶元素，求出下标差，也就是升温的天数，把这个差值记录给栈顶元素在res中的位置。
         * 然后继续看新的栈顶元素，直到当前数字小于等于栈顶元素停止。然后将当前数字入栈。最后返回res。
         * @return: int[]
         * @author: kami
         * @date: 2020/6/7 9:42
         */
        int m = T.length;
        int[] ans = new int[m];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            while (!stack.empty() && T[i] > T[stack.peek()]) {
                int idx = stack.pop();
                ans[idx] = i - idx;
            }
            stack.push(i);
        }
        return ans;
    }

    /**
     * @description: 94. Binary Tree Inorder Traversal
     * Given a binary tree, return the inorder traversal of its nodes' values.
     * @return: 中序遍历节点
     * @author: kami
     * @date: 2020/6/12 22:16
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<Integer> res = new LinkedList<>();
        recursiveInOrder(root, res);
        return res;
    }

    /**
     * @description: 94. Binary Tree Inorder Traversal 递归实现
     * @return:
     * @author: kami
     * @date: 2020/6/12 22:22
     */
    private void recursiveInOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
        } else {
            recursiveInOrder(root.left, res);
            res.add(root.val);
            recursiveInOrder(root.right, res);
        }
    }

    /**
     * @description: 4. Binary Tree Inorder Traversal 迭代实现
     * @return:
     * @author: kami
     * @date: 2020/6/12 22:30
     */
    private List<Integer> iterativeInOrder(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();

        if (root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode p = root;
            while (!stack.isEmpty() || p != null) {

                if (p != null) {      // push left most nodes to stack
                    stack.push(p);
                    p = p.left;
                } else {              // when reach the left end, pop, do something and go right
                    p = stack.pop();
                    result.add(p.val);
                    p = p.right;
                }
            }
        }
        return result;
    }

    /**
     * @description: 102. Binary Tree Level Order Traversal
     * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
     * For example:
     * Given binary tree [3,9,20,null,null,15,7],
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * return its level order traversal as:
     * [
     * [3],
     * [9,20],
     * [15,7]
     * ]
     * @return: 层序遍历得到的列表
     * @author: kami
     * @date: 2020/6/13 17:58
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> wrapList = new LinkedList<>();

        if (root == null) {
            return wrapList;
        }

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                if (queue.peek().left != null) queue.offer(queue.peek().left);
                if (queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }

    /**
     * @description: 105. Construct Binary Tree from Preorder and Inorder Traversal
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     * For example, given
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     * 3
     * / \
     * 9  20
     * /  \
     * 15   7
     * @return: 前序遍历和中序遍历构建二叉树
     * @note: 递归构建子树
     * @author: kami
     * @date: 2020/6/21 10:35
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }

    /**
     * @description: 106. Construct Binary Tree from Inorder and Postorder Traversal
     * Given inorder and postorder traversal of a tree, construct the binary tree.
     * Note:
     * You may assume that duplicates do not exist in the tree.
     * This is my iterative solution, think about "Constructing Binary Tree from inorder and preorder array",
     * @return: 中序遍历和后序遍历构建二叉树
     * @author: kami
     * @date: 2021/2/1 22:01
     */
    public static TreeNode buildTreeFromInAndPost(int[] inorder, int[] postorder) {
        return helperDfs(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    public static TreeNode helperDfs(int[] inorder, int inStart, int inEnd,
                                     int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        // 还原根节点
        TreeNode root = new TreeNode(postorder[postEnd]);
        int pos = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                pos = i;
                break;
            }
        }
        // because pe-ps=pos-is (the number of the rest elements should be equal)
        root.left = helperDfs(inorder, inStart, pos - 1,
                postorder, postStart, postStart + pos - inStart-1);
        // //pe-1-ps=ie-(pos+1) => ps=pe-ie+pos
        root.right = helperDfs(inorder, pos + 1, inEnd,
                postorder, postEnd - inEnd + pos, postEnd - 1);
        return root;
    }

    /**
     * @description: 647. Palindromic Substrings 判断回文字符串子串的个数
     * Given a string, your task is to count how many palindromic substrings in this string.
     * The substrings with different start indexes or end indexes are counted as different substrings even they consist
     * of same characters.
     * @return:
     * @author: kami
     * @date: 2020/6/25 16:39
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int count = s.length();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            for (int j = len - 1; j > i; j--) {
                if (isPalindromicSub(i, j, s)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isPalindromicSub(int startIndex, int endIndex, String str) {
        while (startIndex < endIndex) {
            if (str.charAt(startIndex++) != str.charAt(endIndex--)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description: 230. Kth Smallest Element in a BST
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     * 5
     * / \
     * 3   6
     * / \
     * 2   4
     * /
     * 1
     * Output: 3
     * @author: kami
     * @date: 2020/6/25 17:19
     */
    public int kthSmallest(TreeNode root, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        deepSearch(root, minHeap);
        for (int i = 0; i < k - 1; i++) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

    private void deepSearch(TreeNode root, PriorityQueue<Integer> heap) {
        if (root != null) {
            heap.add(root.val);
            deepSearch(root.left, heap);
            deepSearch(root.right, heap);
        }
    }

    /**
     * @description: 287. Find the Duplicate Number
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
     * find the duplicate one.
     * You must not modify the array (assume the array is read only).
     * You must use only constant, O(1) extra space.
     * Your runtime complexity should be less than O(n2).
     * There is only one duplicate number in the array, but it could be repeated more than once.
     * @return:
     * @author: kami
     * @date: 2020/6/25 18:34
     */
    public int findDuplicate(int[] nums) {
        /**
         * @description: 巧妙的利用坐标和数值之间相互转换，而由于重复数字的存在，那么一定会形成环，用快慢指针可以找到环并确定环的起始位置，
         * 确实是太巧妙了！
         * @return: int
         * @author: kami
         * @date: 2020/6/26 11:12
         */
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            if (slow == nums[slow]) {
                return slow;
            }
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            if (slow == nums[slow]) {
                return slow;
            }
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    /**
     * @description: 287. Find the Duplicate Number
     * @return:
     * @author: kami
     * @date: 2020/6/26 11:10
     */
    public int findDuplicate2(int[] nums) {
        int left = 1, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) >> 1;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    ++cnt;
                }
            }
            if (cnt <= mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    /**
     * @description: 337. House Robber III
     * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
     * called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart
     * thief realized that "all houses in this place forms a binary tree". It will automatically contact the police
     * if two directly-linked houses were broken into on the same night.
     * Determine the maximum amount of money the thief can rob tonight without alerting the police.
     * @return:
     * @author: kami
     * @date: 2020/6/26 11:24
     */
    public int rob(TreeNode root) {
        int[] num = dfs(root);
        return Math.max(num[0], num[1]);
    }

    private int[] dfs(TreeNode x) {
        if (x == null) {
            return new int[2];
        }
        int[] left = dfs(x.left);
        int[] right = dfs(x.right);
        int[] res = new int[2];
        res[0] = left[1] + right[1] + x.val;
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return res;
    }

    /**
     * @description: 114. Flatten Binary Tree to Linked List 变换二叉树为链表
     * Given a binary tree, flatten it to a linked list in-place.
     * For example, given the following tree:
     * 1
     * / \
     * 2   5
     * / \   \
     * 3   4   6
     * The flattened tree should look like:
     * 1
     * \
     * 2
     * \
     * 3
     * \
     * 4
     * \
     * 5
     * \
     * 6
     * @return: 单向链表
     * @author: kami
     * @date: 2020/7/25 17:49
     */
    private TreeNode prev = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    /**
     * @description: 124. Binary Tree Maximum Path Sum
     * Given a non-empty binary tree, find the maximum path sum.
     * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree
     * along the parent-child connections. The path must contain at least one node and does not need to go through
     * the root.
     * @return: 连续路径最大和
     * @author: kami
     * @date: 2020/7/25 18:09
     */
    int maxValue;

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

    /**
     * @description: 128. Longest Consecutive Sequence
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     * Your algorithm should run in O(n) complexity.
     * Example:
     * Input: [100, 4, 200, 1, 3, 2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     * We will use HashMap. The key thing is to keep track of the sequence length and store that in the boundary points
     * of the sequence. For example, as a result, for sequence {1, 2, 3, 4, 5}, map.get(1) and map.get(5) should both
     * return 5.
     * Whenever a new element n is inserted into the map, do two things:
     * See if n - 1 and n + 1 exist in the map, and if so, it means there is an existing sequence next to n. Variables
     * left and right will be the length of those two sequences, while 0 means there is no sequence and n will be the
     * boundary point later. Store (left + right + 1) as the associated value to key n into the map.
     * Use left and right to locate the other end of the sequences to the left and right of n respectively, and replace
     * the value with the new length.
     * @return: 最长连续数字的长度
     * @author: kami
     * @date: 2020/7/25 21:52
     */
    public int longestConsecutive(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = map.getOrDefault(n - 1, 0);
                int right = map.getOrDefault(n + 1, 0);
                // sum: length of the sequence n is in
                int sum = left + right + 1;
                map.put(n, sum);

                // keep track of the max length
                res = Math.max(res, sum);

                // extend the length to the boundary(s)
                // of the sequence
                // will do nothing if n has no neighbors
                map.put(n - left, sum);
                map.put(n + right, sum);
            }
        }
        return res;
    }

    /**
     * @description: 138. Copy List with Random Pointer
     * A linked list is given such that each node contains an additional random pointer which could point to any node
     * in the list or null.
     * Return a deep copy of the list.
     * The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of
     * [val, random_index] where:
     * val: an integer representing Node.val
     * random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not
     * point to any node.
     * @return:
     * @author: kami
     * @date: 2020/7/25 22:23
     */
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        Node iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            Node copy = new Node(iter.val);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        Node pseudoHead = new Node(0);
        Node copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }

    /**
     * @description: 763. Partition Labels
     * A string S of lowercase English letters is given. We want to partition this string into as many parts
     * as possible so that each letter appears in at most one part, and return a list of integers representing
     * the size of these parts.
     * @return: 每段分割的字母的个数列表
     * @author: kami
     * @date: 2021/1/26 20:59
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        dfs(S, list);
        return list;
    }

    private void dfs(String s, List<Integer> list) {
        if (s.length() == 0) {
            return;
        }
        char first = s.charAt(0);
        int last = s.lastIndexOf(first);
        for (int i = 0; i < last; i++) {
            if (s.lastIndexOf(s.charAt(i)) <= last) {
                continue;
            }
            last = s.lastIndexOf(s.charAt(i));
        }
        s = s.substring(last + 1);
        list.add(last + 1);
        dfs(s, list);
    }

}
