package leetcode;

import java.util.*;

public class LeetCode {

    //region 1、Two Sum，找到数组中两数之和为目标值得下标

    //region 1.1 暴力破解，双重for循环，O(n2)
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //endregion

    //region 1.2 使用HashMap<数组元素，索引>，遍历索引i,是否包含target - nums[i]的键，并且值不是自身
    //时间复杂度O(n)，空间复杂度O(n)
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {//并且不能是自身
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //endregion

    //region 1.3 一趟Hash Table，与1.2方法类似，在存入HashMap时与已放入HashMap的对象进行比较，都是O(n)
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    //endregion
    //endregion

    //region 2.Add Two Numbers,输入两个反向链表，对应相加，并返回其链表

    /**
     * Input: (2 -> 4 -> 3) + (5 -> 5 -> 4)
     * Output: 7 -> 9 -> 7
     * Explanation: 342 + 455 = 797
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    //region 2.1  O(max(m,n))，虚拟头结点，进位
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);//返回链表的虚拟头结点
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;//表示进位，如7+5=12>10，carry = 1,carry只能为0,1
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;//将节点p的val值赋给X
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;//对应节点相加，并加上进位
            carry = sum / 10;//更新进位
            curr.next = new ListNode(sum % 10);//添加节点
            curr = curr.next;//返回链表的当前指针，移动指针到下一个节点
            if (p != null) p = p.next;//移动传入链表的当前指针到下一节点
            if (q != null) q = q.next;
        }

        if (carry > 0) {//判断最后一组加和的进位
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
    //endregion


    //endregion

    //region 3.Longest Substring Without Repeating Characters，给定字符串找出其中最长的无重复的子字符串的长度
    //3.1 暴力破解， O(n^3)，
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();//获取字符串长度
        int ans = 0;        //最长不重复字符串的长度
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j))
                    ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }

    //3.2  Sliding Window
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();//用来存储不重复子字符串
        int ans = 0, start = 0, end = 0;
        while (start < n && end < n) {//滑动窗口的起始终止点小于字符串长度
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(end))) {//如果set集合中不包含s[j],
                set.add(s.charAt(end++));//则把把该字符添加到set集合中
                ans = Math.max(ans, end - start);//更新扩大滑动窗口大小
            } else {
                set.remove(s.charAt(start++));//否则从起始点依次去除掉与end点的字符
            }
        }
        return ans;
    }
    //endregion

    // leetcode 4
    public double findMedianSortedArrays(int[] A, int[] B) {
        int lenA = A.length;
        int lenB = B.length;
        int lenC = lenA + lenB;
        int[] C = new int[lenC];
        for (int i = 0; i < lenA; i++) {
            C[i] = A[i];
        }
        for (int i = 0; i < lenB; i++) {
            C[i + lenA] = B[i];
        }
        Arrays.sort(C);
        double result;
        if (lenC % 2 == 0) {
            return (C[lenC / 2] + C[lenC / 2 - 1]) / 2.0;
        } else return C[lenC / 2];
    }

    //leetcode 5
    public static String longestPalindrome(String s) {
        int maxSize = 0;
        int index = 0;
        for (int i = 1; i < s.length(); i++) {
            int j = 1;
            // abcdcba
            while ((i - j) >= 0 && (i + j) < s.length() && s.charAt(i - j) == s.charAt(i + j)) {
                if (j > maxSize) {
                    maxSize = j;
                    index = i;
                }
                j++;
            }
            // abba
            while ((i - j) >= 0 && (i + j) < s.length() && s.charAt(i - j + 1) == s.charAt(i + j)) {
                if (j > maxSize) {
                    maxSize = j;
                    index = i;
                }
                j++;
            }
            while ((i - j) >= 0 && (i + j) < s.length() && s.charAt(i - j) == s.charAt(i + j - 1)) {
                if (j > maxSize) {
                    maxSize = j;
                    index = i;
                }
                j++;
            }

        }
        return s.substring(index - maxSize, index + maxSize);
    }

    public static String longestPalindrome1(String s) {
        if (s.length() == 0) return "";
        int left = 0, right = 0;
        //假设i为中心点
        for (int i = 0; i < s.length(); i++) {
            //单轴中心扩展长度
            int odd = midExpand(s, i, i);
            //双轴中心扩展长度
            int even = midExpand(s, i, i + 1);
            int m = Math.max(odd, even);
            if (m > right - left) {
                //根据每次的长度得到回文串起始索引
                left = i - (m - 1) / 2;
                //获得终点索引
                right = i + m / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    //返回值是回文串的长度
    private static int midExpand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    //leetcode 6

    /**
     * 思路：总体是一个list,元素是每行StringBuilder，依次遍历原字符串，从上到下，然后从下到上.....将字符添加到对应行的             StringBuilder中，最后将每行的StringBuilder连起来
     */
    public String convert(String s, int numRows) {
        if (numRows == 1 || s == null) return s;

        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            list.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            list.get(curRow).append(c);
            if (curRow == 0 || curRow == (numRows - 1)) goingDown = true;
            curRow += goingDown ? 1 : -1;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (StringBuilder sb : list) {
            stringBuilder.append(sb);
        }
        return stringBuilder.toString();
    }

    // leetcode 7  123->321
    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }

        if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
            return 0;
        } else {
            return (int) res;
        }
    }

    //leetcode 8   "-42" -> 42  "kami 1235" -> 0
    public static int myAtoi(String str) {
        str = str.trim();
        if (str == "" || str == "+" || str == "-" || Character.isLetter(str.charAt(0))) return 0;
        int length = str.length();
        int indexEnd = 0;
        boolean firstPos = false, firstNeg = false;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            indexEnd = 1;
            if (str.charAt(0) == '-') firstNeg = true;
            if (str.charAt(0) == '+') firstPos = true;
        }
        for (int i = indexEnd; i < length; i++) {
            if (Character.isDigit(str.charAt(i))) indexEnd++;
        }

        if (firstPos) {
            long result = Long.valueOf(str.substring(1, indexEnd));
            if (result > 1 << 31 - 1 || result < -1 << 31) return 0;
            else return (int) result;
        }
        if (firstNeg) {
            String s = str.substring(1, indexEnd);
            long result = Long.valueOf(str.substring(1, indexEnd));
            if (result > 1 << 31 - 1 || result < -1 << 31) return 0;
            else return (int) -result;
        }

        long last = Long.valueOf(str.substring(0, indexEnd));
        if (last > 1 << 31 - 1 || last < -1 << 31) return 0;
        return (int) last;

    }

    //leetcode 9   101 ->true 12->false 12344321 ->true
    public boolean isPalindrome(int x) {
        String str = String.valueOf(x);
        String str1 = new StringBuilder(str).reverse().toString();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) != str1.charAt(i)) return false;
        }
        return true;
    }

    //leetcode 10 匹配字符串
    /**
     * some examples:
     * isMatch{"aa","a"}   false
     * isMatch{"aa","aa"}   true
     * isMatch{"aaa","a"}   false
     * isMatch{"aa","a*"}   true
     * isMatch{"aa",".*"}   true
     * isMatch{"ab",".*"}   true
     * isMatch{"aa","a"}   true
     * isMatch{"aab","c*a*b"}   true
     * s string, p patten
     * boolean dp[i][j] s[0-i][0-j]是否匹配
     * dp[0][0] 初始化
     * 条件分析
     * 1. p.charAt(j) == s,charAt(i)  更新 dp[i][j] = dp[i-1][j-1]跟前一级保持同步         如 "a","a"
     * 2. if p.charAt(j) == '.'      更新 dp[i][j] = dp[i-1][j-1]                             "a", "."
     * 3. if p.chharAt(j) == '*',较为复杂                                                     "a", "*"
     *      3.1 if p.charAt(j-1) != s.charAt(i) 更新 dp[i][j] = dp[i][j-2]  a* counts empty      "a","b*"
     *      3.2 if p.charAt(j-1) == s.charAt(i) || p.charAt(j-1) == '.'                           "a","a*" 或者 "a", ".*"
     *          1.dp[i][j] = dp[i][j-1] a* counts single a                                          三者中只要有一个是true即可
     *          2.dp[i][j] = dp[i-1][j] a* counts multiple a
     *          3.dp[i][j] = dp[i][j-2] a* counts empty
     */
    public boolean isMatch (String s, String p){
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;

        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = (i < s.length() &&
                        (p.charAt(j) == s.charAt(i) ||
                                p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static boolean isMatch1 (String s, String p){
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        //预处理
        dp[0][0] = true;
        for (int i = 0; i < p.length() ; i++) {
            // "aab" ,"c*aab"
            if (p.charAt(i) == '*' && dp[0][i-1]) {
                dp[0][i+1] = true;
            }
        }
        //条件分析
        for (int i = 0; i < s.length() ; i++) {
            for (int j = 0; j < p.length() ; j++) {
                if (p.charAt(j) == s.charAt(i))  dp[i+1][j+1] = dp[i][j];
                if (p.charAt(j) == '.')  dp[i+1][j+1] = dp[i][j];
                if (p.charAt(j) == '*') {
                    if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.')
                        dp[i+1][j+1] = dp[i+1][j-1];
                    else dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    // leetcode 11  Container With Most Water
    public int maxArea(int[] height) {
        if (height == null) return 0;
        int max = 0;
        int len = height.length;
        for (int i = 0; i <len -1 ; i++) {
            for (int j = i+1; j < len ; j++) {
                int minHeight = Math.min(height[i],height[j]);
                max = (j-i)*minHeight > max ? (j-i)*minHeight : max;
            }
        }
        return max;
    }
        // 双指针， start,end
    public int maxArea1(int[] height){
        int start = 0,end = height.length-1;
        int max = 0;
        while (start < end){
            int tempArea = (end-start) * Math.min(height[start],height[end]);
            if (tempArea > max) {
                max = tempArea;
            }
            if (height[start] > height[end]) end--;
            else start++;
        }
        return max;
    }

    //leetcode 12 Integer to Roman
    public String intToRoman(int num) { //2543
        int[] N = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] S = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < N.length ; i++) {
            while (num >= N[i]){
                stringBuilder.append(S[i]);
                num -= N[i];
            }
        }
        return stringBuilder.toString();

    }

    //leetcode 13 Roman to Integer "MCMXCIV" -> 1994
    public int romanToInt(String s) {
        //int[] N = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        Map<Character,Integer> map = new HashMap();
        map.put('M',1000);
        map.put('D',500);
        map.put('C',100);
        map.put('L',50);
        map.put('X',10);
        map.put('V',5);
        map.put('I',1);
        int result = 0;
        for (int i = 0; i <s.length(); i++) {
            if ( i+1 < s.length() &&  map.get(s.charAt(i)) < map.get(s.charAt(i+1)) ){
                result += (map.get(s.charAt(i+1)) - map.get(s.charAt(i)));
                i++;
            }
            else result += map.get(s.charAt(i));
        }

        return result;
    }

    //leetcode 14 Longest Common Prefix    Input: ["flower","flow","flight"]     Output: "fl"
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    //leetcode 15 3Sum   [ [-1, 0, 1], [-1, -1, 2] ]
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//对原数组排序
        for (int i = 0; i <nums.length ; i++) {
            //定义两个指针
            int targt = -nums[i];
            int left = i+1,right = nums.length-1;
            while (left < right){
                if (nums[left] + nums[right] > targt) right--;
                else if (nums[left] + nums[right] < targt) left++;
                else {
                    List<Integer> list = Arrays.asList(nums[i],nums[left++],nums[right--]);
                    if (!result.contains(list)) result.add(list);
                }
            }

        }
        return result;
    }
}

