package leetcode;

import java.util.*;

public class LeetCode5 {
    //64. Minimum Path Sum
    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        return minPathSumHelper(grid, 0, 0, memo);
    }

    public int minPathSumHelper(int[][] grid, int row, int col, int[][] memo) {
        if(row == grid.length-1 && col == grid[0].length-1) return grid[row][col];
        if(memo[row][col] != 0) return memo[row][col];

        int rowInc = Integer.MAX_VALUE, colInc = Integer.MAX_VALUE;
        if(row < grid.length-1) rowInc = minPathSumHelper(grid, row+1, col, memo);
        if(col < grid[0].length-1) colInc = minPathSumHelper(grid, row, col+1, memo);
        memo[row][col] = Math.min(rowInc, colInc) + grid[row][col];
        return memo[row][col];
    }

    //65. Valid Number
    public boolean isNumber(String s) {
        s = s.trim();//净化字符串
        boolean pointSeen = false;//是否有点
        boolean eSeen = false;//是否有 e
        boolean numberSeen = false;//是否有数字
        boolean numberAfterE = true;//e后是否有数字
        for (int i = 0; i < s.length(); i++) {
            char iChar = s.charAt(i);
            //字符是否是在0-9之间
            if ('0' <= iChar && iChar <= '9'){
                numberSeen = true;
                numberAfterE = true;
            } else if (iChar == '.'){
                // e 和 . 同时存在，返回false
                if (eSeen || pointSeen){
                    return false;
                }
                pointSeen = true;
            } else if (iChar == 'e'){
                // 如果 e 前面没有数字，返回false
                if (eSeen || !numberSeen){
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if (iChar == '-' || iChar == '+'){
                //如果在中间位置，出现类似 3-65这种情况，返回false， 必须得是 125e-5类似
                if (i != 0 && s.charAt(i-1) != 'e'){
                    return false;
                }
            }else {
                return false;
            }
        }
        //返回 有数字 并且 e后有数字；numberAfterE初始化为true
        return numberSeen && numberAfterE;
    }

    //66. Plus One Input: [1,2,3]  Output: [1,2,4]
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >=0; i--) {
            if (digits[i] != 9) {
                digits[i]++;
                break;
            } else {
                digits[i] = 0;
            }
        }
        if (digits[0] == 0) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            return res;
        }
        return digits;
    }

    //67. Add Binary input: a = "11", b = "1"  Output: "100"
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    //68. Text Justification,字符串数组按照每行最多放置的字符数存放，返回List<String>
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int index = 0,len = words.length;
        //遍历字符串数组
        while (index < len){

            int count = words[index].length();//当前字符串长度
            int last = index + 1;
            //判断每一行存放的字符串数组 起始索引
            while (last < len){
                if (words[last].length() + count + 1  > maxWidth) break;
                count += words[last].length() + 1;
                last++;
            }
            StringBuilder stringBuilder = new StringBuilder();
            int diff = last - index - 1;
            //当前字符串为最后一组 或者 maxWidth只能存放某一个字符串
            if (last == len || diff == 0){
                for (int i = index; i < last; i++) {
                    stringBuilder.append(words[i] + " ");
                }
                //去掉最后添加的一个字符串后的空格
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                //如果当前行字符串占位小于maxWidth，补空格
                for (int i = stringBuilder.length(); i < maxWidth; i++) {
                    stringBuilder.append(" ");
                }
            }else {
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index; i < last; i++) {
                    stringBuilder.append(words[i]);
                    if (i < last - 1){
                        for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
                            stringBuilder.append(" ");
                        }
                    }
                }
            }
            res.add(stringBuilder.toString());
            index = last;

        }
        return res;
    }

    //Implement int sqrt(int x) Input: 4 Output: 2
    public int mySqrt(int x) {
        return (int)Math.sqrt(x);
    }

    //70. Climbing Stairs, Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    public int climbStairs(int n) {
        if (n == 1) return 1;
        else if (n == 2) return 2;
//        else return climbStairs(n) + climbStairs(n-1);
        int res = 0;
        int pre1 = 1;
        int pre2 = 2;
        for (int i = 3; i < n; i++) {
            res = pre1 + pre2;
            pre1 = pre2;
            pre2 = res;
        }
        return res;
    }

    //71. Simplify Path Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.
    /**                /a/../../b/../c//.//    "/c"
     * 重复连续出现的'/'，只按1个处理，即跳过重复连续出现的'/'；
     * 如果路径名是"."，则不处理；
     * 如果路径名是".."，则需要弹栈，如果栈为空，则不做处理；
     * 如果路径名为其他字符串，入栈。
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        String res = "";
        for (String dir : stack) res = "/" + dir + res;
        return res.isEmpty() ? "/" : res;
    }

    //72. Edit Distance   Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2
    public int minDistance(String word1, String word2) {
        int m = word1.length(),n = word2.length();
        int[][] dp = new int[m+1][n+1];////dp[i][j]表示word1长度为i --> word2长度为j 所需步数
        //数组初始化
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i],m+n);//每个值填充为可能最大值
            dp[i][0] = i;//长度为i的Word1 --》0 需要去掉i个字符
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;//长度为0的Word1 --> 长度为i的word2,需要添加i个字符
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c1 = word1.charAt(i);
                char c2 = word2.charAt(j);
                if (c1 == c2){
                    dp[i+1][j+1] = dp[i][j];//字符同，无操作，步数与上次结果相等
                }else {
                    //字符异，取三者步数最小，1.删去Word2[j] 2.删去Word1[i] 3.替换word2[j] = word[i]
                    dp[i+1][j+1] = Math.min(dp[i+1][j],Math.min(dp[i][j+1],dp[i][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    //73. Set Matrix Zeroes Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.
    public void setZeroes(int[][] matrix) {
        int width = matrix[0].length,height = matrix.length;
        boolean[] rowHasZero = new boolean[height];
        boolean[] colHasZero = new boolean[width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0){
                    rowHasZero[i] = true;
                    colHasZero[j] = true;
                }
            }
        }
        for (int i = 0; i < height; i++) {
            if (rowHasZero[i]){
                Arrays.fill(matrix[i],0);
            }
        }
        for (int i = 0; i < width; i++) {
            if (colHasZero[i]){
                for (int j = 0; j < height; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

    }

    //74. Search a 2D Matrix
    public  boolean searchMatrix(int[][] matrix, int target) {

        //<editor-fold desc="暴力求解">
//        int r = matrix.length ;if (matrix.length == 0) return false;
//        int c = matrix[0].length;if (c == 0) return false;
//        if (target < matrix[0][0] || target > matrix[r-1][c-1]) return false;
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                if (matrix[i][j] == target)
//                    return true;
//            }
//        }
//        return false;
        //</editor-fold>
        int r = matrix.length ;if (r == 0) return false;
        int c = matrix[0].length;if (c == 0) return false;
        if (target < matrix[0][0] || target > matrix[r-1][c-1]) return false;
        int start = 0;
        int end = (r*c)-1;
        int colLength = c;

        while(start<=end){
            int mid = (start+end)/2;

            int tempRow = (mid/colLength);
            int tempCol = (mid%colLength);

            if(matrix[tempRow][tempCol] == target) return true;
            if(matrix[tempRow][tempCol]<target){
                start = mid+1;
            }else{
                end = mid-1;
            }
        }
        return false;

    }

    //75. Sort Colors Input: [2,0,2,1,1,0]  Output: [0,0,1,1,2,2]
    public static void swap(int a,int b){
        int temp = a;
        a = b;
        b = temp;
    }
    public void sortColors(int[] nums) {
        int m = 0;
        int k = nums.length ;

        for(int i=0; i< k; i++) {
            if(nums[i] == 0) {
                swap(nums[i],nums[m]);
                m++;
            }else if(nums[i] == 2) {
                swap(nums[i],nums[k]);
                k--;
                i--;
            }
        }
    }

    //76. Minimum Window Substring Input: S = "ADOBECODEBANC", T = "ABC"  Output: "BANC"
    public static String minWindow(String s, String t) {
        //相当于t中字符和出现次数的映射，如 map['c'] = 4,'c'在计算机中存储为一个数字
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c] += 1;
        }
        int begin = 0;//窗口开始index
        int len = Integer.MAX_VALUE;//窗口大小
        int count = t.length();//t的长度
        for (int left=0, right=0; right<s.length(); right++) {
            char c = s.charAt(right);
            map[c]--;//对应字符次数减1
            if(map[c] >= 0) count--;//如果某个字符在窗口中还存在，则窗口大小减1，注  原始窗口大小为t的长度
            //当S中到某个字符时，前面已经包含了t中所有字符  Input: S = "ADOBE C ODEBANC", T = "ABC"  Output: "BANC"
            while (count == 0) {
                char lc = s.charAt(left);
                map[lc]++;
                //第一个S中对应T中字符
                if (map[lc]>0) {
                    if (right-left+1<len) {
                        begin = left;
                        len = right-left+1;
                    }
                    count++;
                }
                left++;
            }
        }
        return len == Integer.MAX_VALUE ? "": s.substring(begin, begin+len);
    }

    //77.Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
    /**
     * @discription 数字全排列，5,3 ---> 123,124,125,234,235,245,345
     * 使用回朔算法最合适,
     * 回溯是遍历搜索空间所有可能组态的方法。这些组态也许代表对象的所有排列或这是构建对象集合的所有可能的方法（子集）。
     * 其他情况包括列举一个图的所有生成树，两个节点的所有路径或是把节点分类成不同颜色的所有不同的方式。
     * @date 2019/8/20 22:37
     **/
    public static List<List<Integer>> combine(int n, int k) {
       List<List<Integer>> result = new ArrayList<>();
       backtrackCombine(result,new ArrayList<>(),1,n,k);
       return result;
     }

     public static void backtrackCombine(List<List<Integer>> result,ArrayList<Integer> item,int start,int n,int k){
        if (k == 0){
            result.add(new ArrayList<>(item));
            return;
        }
         for (int i = start; i <= n; i++) {
             item.add(i);
             backtrackCombine(result,item,i+1,n,k-1);
             item.remove(item.size()-1);
         }
     }

    /**
     * @discription 78. Subsets， Given a set of distinct integers, nums, return all possible subsets (the power set).
     * 给定数组，求其无重复子集
     * @date 2019/9/6 22:23
     **/
     public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= n ; i++) {
            backtrackSubset(result,new ArrayList<>(),nums,1,n,i);
        }
        return result;
     }
     public static void backtrackSubset(List<List<Integer>> result,List<Integer> item,int[] nums,int start,int n,int k){
         if (k == 0){

             result.add(new ArrayList<>(item));
             return;
         }
         for (int i = 0; i < n ; i++) {
             if (!item.contains(nums[i])) item.add(nums[i]);
             backtrackSubset(result,item,nums,i+1,n,k-1);
             if (item.size() != 0)item.remove(item.size()-1);
         }
     }

     /**
      * @discription 79 word search
      * Given a 2D board and a word, find if the word exists in the grid.
      * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those
      * horizontally or vertically neighboring. The same letter cell may not be used more than once.
      * @date 2019/10/1 8:09
      **/
     static boolean[][] visited;
    public  boolean exist(char[][] board, String word) {
        int height = board.length,width = board[0].length;
        visited = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (word.charAt(0) == board[i][j] && search(board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean search(char[][] board,String word,int i,int j,int index){
        if (index == word.length()){
            return true;
        }
        if (i<0 || i>=board.length || j<0 || j>= board[0].length ||
                board[i][j] != word.charAt(index) || visited[i][j]){
            return false;
        }
        visited[i][j] = true;
        if (search(board,word,i-1,j,index+1) ||
            search(board,word,i+1,j,index+1) ||
            search(board,word,i,j-1,index+1) ||
            search(board,word,i,j+1,index+1) ){
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    //86.Partition List排序链表，小值在前，大值在后，相对顺序不变
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode small = smallHead;
        ListNode bigHead = new ListNode(0);
        ListNode big = bigHead;
        while (head != null){
            if (head.val < x){
                small.next = head;
                small = small.next;
            }else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        big.next = null;
        small.next = bigHead.next;
        return smallHead.next;
    }

    /**
     * @description: 87. Scramble String
     * We say that "rgeat" is a scrambled string of "great".
     * The base case that s1 can scramble into s2 if s1== s2. If the frequencies of each characters appearing in s1 and s2 differ,
     * then s1 can not scramble into s2.
     * If there exist 0 <= i <= s1.length() where
     * s1[0,i] can scramble into s2[0,i] and s1[i,length] can scramble into s2[i, length]; or
     * s1[0,i] can scramble into s2[length - i, length] and s1[i,length] can scramble into s2[0, length - i]
     * then, s1 can scramble into s2.
     * @author: kami
     * @time: 2019/12/12 9:50
     */
    public static boolean isScramble(String s1, String s2) {
        if( s1.equals(s2) )
            return true;
        int[] s1Array = new int[26];
        int[] s2Array = new int[26];
        for(int i = 0; i < s1.length(); i++) {
            char item1 = s1.charAt(i);
            char item2 = s2.charAt(i);
            s1Array[item1 - 'a']++;
            s2Array[item2 - 'a']++;
        }
        for(int i = 0; i < 26; i++)
            if( s1Array[i] != s2Array[i] )
                return false;

        for(int i = 1; i < s1.length(); i++) {
            if( isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i)) )
                return true;
            if( isScramble(s1.substring(0, i), s2.substring(s1.length() - i))
                    && isScramble(s1.substring(i), s2.substring(0, s1.length() - i)))
                return true;
        }
        return false;
    }

    /**
     * @description: 88. Merge Sorted Array
     * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
     * Note:
     * The number of elements initialized in nums1 and nums2 are m and n respectively.
     * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
     * Input:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     * Output: [1,2,2,3,5,6]
     * 从后往前比较，大数先放置,依次往前走；
     * 分析：因为两个数组合并后的有效数字的个数是确定的，所以可以从后往前一次排
     * @author: kami
     * @time: 2019/12/12 13:34
     */   
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        while(n>0) nums1[m+n-1] = (m==0||nums2[n-1] > nums1[m-1]) ? nums2[--n] : nums1[--m];
        for (int a:nums1) {
            System.out.println(a);
        }
    }

    /**
     * @description: 89. Gray Code
     * The gray code is a binary numeral system where two successive values differ in only one bit
     * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
     * A gray code sequence must begin with 0.
        Input: 2
        Output: [0,1,3,2]
        Explanation:
        00 - 0
        01 - 1
        11 - 3
        10 - 2
     * @author: kami
     * @time: 2019/12/12 16:58
     */   
    public static List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        for (int i = 1; i < Math.pow(2,n); i *= 2) {
            for (int j = res.size()-1; j >= 0 ; j--) {
                res.add(i + res.get(j));
            }
        }
        return res;
    }
    /**
     * @description: 89格雷码，backtracking方法;
     * @return:
     * @auther: kami
     * @date: 2020/3/1 22:06
     */
    public static List<Integer> grayCode2(int n){
        List<Integer> code = new ArrayList<>();
        code.add(0);
        genCode(code,n,0,1<<n);
        return code;
    }
    private static void genCode(List<Integer> code,int n,int curr,int size){
        if (code.size() == size) return;
        for (int i = 0; i < n; i++) {
            int mask = 1 << i;
            int e1 = curr^mask;
            if (!code.contains(e1)){
                code.add(e1);
                genCode(code,n,e1,size);
            }
        }
    }

    /**
     * @description: 621. Task Scheduler，https://leetcode.com/problems/task-scheduler/solution/
     * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different
     * letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval.
     * For each interval, CPU could finish one task or just be idle.
     * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals
     * that CPU are doing different tasks or just be idle.
     * You need to return the least number of intervals the CPU will take to finish all the given tasks.
     * Input: tasks = ["A","A","A","B","B","B"], n = 2
     * Output: 8
     * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
     * Note:
     * The number of tasks is in the range [1, 10000].
     * The integer n is in the range [0, 100].
     * 思路：维护以窗口大小为n的任务书最多的n个不同任务，每次优先执行任务数最大的n个不同任务
     * @author: kami
     * @time: 2019/12/12 17:03
     */   
    public int leastInterval(char[] tasks, int n) {


        //<editor-fold desc="1.每个空闲循环对剩余数量统计排序">
//        int[] countMap = new int[26];
//        for (char c: tasks)
//            countMap[c - 'A']++;
//        Arrays.sort(countMap);
//        int time = 0;
//        while (countMap[25] > 0) {
//            int i = 0;
//            while (i <= n) {
//                if (countMap[25] == 0)
//                    break;
//                if (i < 26 && countMap[25 - i] > 0)
//                    countMap[25 - i]--;
//                time++;
//                i++;
//            }
//            Arrays.sort(countMap);
//        }
//        return time;
        //</editor-fold>

        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        PriorityQueue < Integer > queue = new PriorityQueue < > (26, Collections.reverseOrder());
        for (int f: map) {
            if (f > 0)
                queue.add(f);
        }
        int time = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            List < Integer > temp = new ArrayList < > ();
            while (i <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1)
                        temp.add(queue.poll() - 1);
                    else
                        queue.poll();
                }
                time++;
                if (queue.isEmpty() && temp.size() == 0)
                    break;
                i++;
            }
            for (int l: temp)
                queue.add(l);
        }
        return time;
    }

    /**
     * @description: 171. Excel Sheet Column Number,Given a column title as appear in an Excel sheet, return its corresponding column number.
     *      A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     * @author: kami
     * @time: 2019/12/21 9:47
     */
    public int titleToNumber(String s) {
        int sum = 0;
        int A = 'A';
        int length = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < length; i++) {
            sum *= 26;
            sum += (chars[i] - A + 1);
        }
        return sum;
    }

    /**
     * @description:
     * @author: kami
     * @time: 2019/12/20 11:28
     */   
    public static void main(String[] args) {
        System.out.println(grayCode(3));
    }

}
