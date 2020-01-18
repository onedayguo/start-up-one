package leetcode;

import java.util.*;

public class LeetCode1 {
    //leetcode 16. 3Sum Closest
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int numLen = nums.length;
        int result = nums[0] + nums[1] + nums[numLen-1];
        for (int i = 0; i < numLen; i++) {
            int left = i+1,right = numLen-1;
            while (left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum-target) < Math.abs(result-target)){
                    result = sum;
                    if (sum-target < 0) left++;
                    else right--;
                }
                else if (sum - target < 0) left++;
                else right--;
            }
        }
        return result;
    }

    //leetcode 17. Letter Combinations of a Phone Number
    Map<String, String> phone = new HashMap() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    List<String> output = new ArrayList<>();
    public void backtrack(String combination, String next_digits) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1));
            }
        }
    }
    public List<String> letterCombinations1(String digits) {

        if (digits.length() != 0)
            backtrack("", digits);
        return output;
    }

    public static List<String> letterCombinations2(String digits) {
        List<String> answer = new ArrayList<>();

        if(digits.length() < 1){
            return answer;
        }
        String [] mapLeters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        answer.add("");
        for(int x=0;x<digits.length();x++){  //遍历数字串
            int curDigit = Integer.parseInt(digits.substring(x, x+1)); //得到当前位置数字，转化为int
            String curLeters = mapLeters[curDigit];//获取该数字代表的对应字符串

            int ansSize = answer.size();  //
            for(int y=0;y<ansSize;y++){
                String current = answer.remove(0);
                for(int z=0;z<curLeters.length();z++){
                    answer.add(current + curLeters.charAt(z));
                }
            }
        }
        return answer;
    }

    //leetcode 18 4Sum
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int numsLen = nums.length;
        for (int i = 0; i < numsLen; i++) {
            for (int j = i+1; j < numsLen-2; j++) {
                int left = j+1,right = numsLen -1;
                while (left<right){
                    int sum= nums[i]+nums[j]+nums[left]+nums[right];
                    if (sum < target) left++;
                    else if (sum > target) right--;
                    else {
                        List<Integer> list = Arrays.asList(nums[i],nums[j],nums[left++],nums[right--]);
                        if (!ans.contains(list)) ans.add(list);
                    }
                }
            }
        }
        return ans;
    }

    //leetcode 19 Remove Nth Node From End of List
//    public static class ListNode {
//        int val;
//        ListNode next;
//        public ListNode(int x) { val = x; }
//    }
    //leetcode 20 1->2->3->4->5, and n = 2.   1->2->3->5.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) return null;
        int count = 0;
        ListNode headA = head;
        ListNode headB = head;
        while (headA != null) {
            count++;
            headA = headA.next;
        }

        int pos = count - n,i = 0;
        if (pos == 0) return head.next;
        while (headB != null){
            if (i == pos-1){
                headB.next = headB.next.next;
                break;
            }
            i++;
            headB = headB.next;
        }
        return head;
    }

    public ListNode removeNthFromEnd1(ListNode head, int n){
        if (head.next == null) return null;
        int count = 1;
        ListNode headA = head,headB = head;
        while (headA != null){
            if (count == n) {
                headB = headB.next;
            }
            else {
                count++;
                headA = headA.next;
            }
        }
        headB = headB.next.next;
        return head;

    }

    //leetcode 20. Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push('0');
        for (Character c:s.toCharArray()) {
            switch (c){
                case '(':stack.push(c);break;
                case '{':stack.push(c);break;
                case '[':stack.push(c);break;
                case ')':
                    if (stack.pop() == '(') break ;
                    else return false;
                case '}':
                    if (stack.pop() == '{') break;
                    else return false;
                case ']':
                    if (stack.pop() == '[') break;
                    else return false;
            }
        }
        return stack.pop() == '0';
    }

    //leetcode 21 Merge Two Sorted Lists   Input: 1->2->4, 1->3->4   Output: 1->1->2->3->4->4
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return  l2;
        if (l2 == null) return  l1;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2!= null){
            if (l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
                cur = cur.next;
            }
            else {
                cur.next = l2;
                l2 = l2.next;
                cur = cur.next;
            }
        }
        while (l1 != null){
            cur.next = l1;
            l1=l1.next;
            cur = cur.next;
        }
        while (l2 != null){
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    //leetcode 22. Generate Parentheses
    public static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        backtrack("", res, n, n);
        return res;
    }

    public static void backtrack(String sublist, List<String> res, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(sublist);
            return;
        }
        if (left > right)
            return;
        if (left > 0)
            backtrack(sublist + "(", res, left - 1, right);
        if (right > 0)
            backtrack(sublist + ")", res, left, right - 1);
    }

    // leetcode 23. Merge k Sorted Lists
    public  static ListNode mergeKLists1(ListNode[] lists){
        // checking size since PriorityQueue cannot have initial size of 0.
        if (lists == null || lists.length == 0) {
            return null;
        }

        Queue<ListNode> minHeap = new PriorityQueue<>(
                lists.length,
                (node1, node2) -> Integer.compare(node1.val, node2.val)
        );

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.remove();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) {
                minHeap.add(node.next);
            }
        }

        return dummy.next;
    }

    //leetcode 24 Swap Nodes in Pairs
    public ListNode swapPairs(ListNode head) {
        if (head == null) { return null; }
        if (head.next == null) { return head; }

        ListNode next = head.next;
        ListNode following = next.next;
        head.next = swapPairs(following);
        next.next = head;

        return next;
    }

    //leetcode 25  Reverse Nodes in k-Group
    public ListNode reverseKGroup(ListNode head, int k) {
        int i = 0;
        ListNode last = head;
        //判断链表长度是否小于 k
        while (i < k) {
            if (last == null) {
                return head;
            }
            last = last.next;
            i++;
        }
        ListNode dummy = head;
        ListNode next = head.next;
        for (i = 0; i < k - 1; i++) {
            ListNode following = next.next;
            next.next = head;
            head = next;
            next = following;
        }
        dummy.next = reverseKGroup(last, k);
        return head;
    }

    public static ListNode reverseKGroup1(ListNode head, int k){
        ListNode cur = head;
        ListNode ans = new ListNode(-1);
        ListNode fisrt = ans;
        ListNode temp = null;
        Stack<ListNode> stack = new Stack<>();
        while (cur != null){
            if (stack.size() == k){
                while (stack.size() > 0){
                    ans.next = stack.pop();
                    ans = ans.next;
                }
            }

            stack.push(cur);
            temp = cur;
            cur = cur.next;
            temp.next = null;

        }

        Collections.reverse(stack);
        while (stack.size() > 0){
            ans.next = stack.pop();
            ans = ans.next;
        }
        return fisrt.next;
    }

    //leetcode 26 Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        if (nums == null ) return  0;
        if (nums.length == 1) return 1;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[count] != nums[i]) {
                nums[++count] = nums[i];
            }
        }
        return count +1;

    }

    //leetcode 27. Remove Element
    public int removeElement(int[] nums, int val) {
        if (nums == null) return 0;
        if (nums.length == 1 && nums[0] == val) return 0;
        int count = 0;
        for (int i = 0; i < nums.length ; i++) {
            if (nums[i] != val) nums[count++] = nums[i];
        }
        return count;
    }

    //leetcode 28 Implement strStr() haystack = "hello", needle = "ll" Output: 2
    public static int strStr(String haystack, String needle) {
        int needleLen = needle.length(),haystackLen = haystack.length();
        if (haystack.isEmpty() && needle.isEmpty() ) return 0;
        if (!haystack.isEmpty() && needle.isEmpty() ) return 0;
        if (haystack.isEmpty() && !needle.isEmpty() ) return -1;
        if (haystackLen < needleLen) return -1;
        for (int i = 0; i < haystackLen ; i++) {
            int startN = 0,startH = i;
            while (haystack.charAt(startH) == needle.charAt(startN)){
                if (startH == haystackLen -1 && needleLen ==1 ) return i;
                if (startN == needleLen - 1) return i;
                if (startH == haystackLen -1 && startN < needleLen-1) return -1;
                startH++;
                startN++;
            }
        }
        return -1;

    }

    public static int strStr1(String haystack, String needle){
        int needleLen = needle.length(),haystackLen = haystack.length();
        if (haystack.isEmpty() && needle.isEmpty() ) return 0;
        if (!haystack.isEmpty() && needle.isEmpty() ) return 0;
        if (haystack.isEmpty() && !needle.isEmpty() ) return -1;
        if (haystackLen < needleLen) return -1;
        return haystack.indexOf(needle);
    }

    //LeetCode 29 Divide Two Integers
    public static int divide(int dividend, int divisor) {
        if (dividend == -Integer.MAX_VALUE) dividend = 1+dividend;
        if (Math.abs(dividend) < Math.abs(divisor)) return 0;
        if (dividend == divisor) return 1;
        if (dividend < 0 && divisor < 0) {
            dividend = ~dividend;
            divisor = ~divisor;
        }
        boolean negPosi = false;
        if ((dividend<0 && divisor>0 )|| (dividend>0 && divisor<0)) {
            dividend = Math.abs(dividend);
            divisor = Math.abs(divisor);
            negPosi = true;
        }
        long count = 0;
        long temp =0;
        do {
            temp += divisor;
            count++;
            if (count > Integer.MAX_VALUE ) return Integer.MAX_VALUE;
        }while (dividend - temp > divisor);
        //int a = (int) (count =  negPosi ? -count:count);
        return (int) (count =  negPosi ? -count:count);
    }
    //submit
    public int divide1(int dividend, int divisor) {
            boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ? true : false;
            long absDividend = Math.abs((long) dividend);
            long absDivisor = Math.abs((long) divisor);
            long result = 0;
            while(absDividend >= absDivisor){
                long tmp = absDivisor, count = 1;
                while(tmp <= absDividend){
                    tmp <<= 1;
                    count <<= 1;
                }
                result += count >> 1;
                absDividend -= tmp >> 1;
            }
            return  isNegative ? (int) ~result + 1 : result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
        }
}
