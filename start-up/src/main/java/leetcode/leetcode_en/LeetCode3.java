package leetcode.leetcode_en;


import java.util.*;

public class LeetCode3 {
    //43. Multiply Strings  Input: num1 = "123", num2 = "456" Output: "56088"，不能直接用内置方法将入参转化为数字
    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];

        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }

    //44. Wildcard Matching s = "adceb"  p = "*a*b"  Output: true
    //'?' Matches any single character.
    //'*' Matches any sequence of characters (including the empty sequence).
    //动态规划
    public static boolean isMatch(String s, String p) {
        boolean[][] match=new boolean[s.length()+1][p.length()+1];
        match[s.length()][p.length()]=true;
        for(int i=p.length()-1;i>=0;i--){
            if(p.charAt(i)!='*')
                break;
            else
                match[s.length()][i]=true;
        }
        for(int i=s.length()-1;i>=0;i--){
            for(int j=p.length()-1;j>=0;j--){
                if(s.charAt(i)==p.charAt(j)||p.charAt(j)=='?')
                    match[i][j]=match[i+1][j+1];
                else if(p.charAt(j)=='*')
                    match[i][j]=match[i+1][j]||match[i][j+1];
                else
                    match[i][j]=false;
            }
        }
        return match[0][0];
    }
    //斐波那契数列递归法
    public static int fib(int n){
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fib(n-1) + fib(n-2);
    }
    //优化1 自顶向下的备忘录法
    public static int fib1(int n){
        if (n <= 0) return 0;
        int[] memory = new int[n+1];
        for (int i = 0; i < n+1 ; i++) {
            memory[i] = -1;
        }
        return fibTopToBotom(n,memory);
    }
    public static int fibTopToBotom(int n,int[] memory){
        //如果已经求出了fib（n）的值直接返回，否则将求出的值保存在Memo备忘录中
        if (memory[n] != -1) return memory[n];
        if (n <= 2) memory[n] = 1;
        else memory[n] = fibTopToBotom(n-1,memory) + fibTopToBotom(n-2,memory);
        return memory[n];
    }
    //优化2 自底向上的动态规划,先把子问题都求出来
    public static int fib2(int n){
        if (n<=0) return n;
        int[] memory = new int[n+1];
        memory[0] = 0;
        memory[1] = 1;
        for (int i = 2; i < n+1; i++) {
            memory[i] = memory[i-1] + memory[i-2];
        }
        return memory[n];
    }
    //优化3 自底向上 不使用数组
    public static int fib3(int n){
        if (n<=2) return 1;
        int mem_i_2 = 1;
        int mem_i_1 = 1;
        int mem_i = 2;
        for (int i = 3; i <= n; i++) {
            int temp = mem_i;//2
            mem_i = mem_i_1 + mem_i_2;//2
            mem_i_2 = mem_i_1;//1
            mem_i_1 = temp;//2
        }
        return mem_i;
    }
    //判断
    public static boolean isMatch1(String s,String p){
        int sp = 0;
        int pp = 0;
        int match = 0;
        int star = -1;
        while (sp < s.length()){
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')){
                sp++;
                pp++;
            }else if (pp < p.length() && p.charAt(pp) == '*'){
                star = pp;
                match = sp;
                pp++;
            }else if (star != -1){
                pp = star+1;
                match++;
                sp = match;
            }else return false;
        }
        while (pp < p.length() && p.charAt(pp) == '*') pp++;
        return pp == p.length();
    }

    //45. Jump Game II
    //不关注路径，只关注最小步数。如果后一个数比前一个数大1以上，则前后两个数应该为较大的那个数，前数-1.则最小步数不变
    // 3, 2 ，1 ，0，4
    public int jump(int[] nums) {
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            nums[i] = Math.max(nums[i],nums[i-1]-1);
        }
        int jump = 0;
        int index = 0;
        while (index < len-1){
            jump++;//步数+1
            index+=nums[index];//更新索引
        }
        return jump;
    }

    //46. Permutations,找混合子集Input: [1,2,3]  Output: [ [1,2,3], [1,3,2], [2,1,3],[2,3,1], [3,1,2], [3,2,1] ]
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //region 回溯法解决的问题
    //1.求数组的子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack0(list, new ArrayList<>(), nums, 0);
        return list;
    }
    private static void backtrack0(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            tempList.add(nums[i]);
            backtrack0(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //2.求数组的子集 contains duplicates
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack1(list, new ArrayList<>(), nums, 0);
        return list;
    }
    private static void backtrack1(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
        list.add(new ArrayList<>(tempList));
        for(int i = start; i < nums.length; i++){
            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack1(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }


    //endregion

    //47. Permutations II 找混合子集Input: [1,1,2] Output:[ [1,1,2], [1,2,1], [2,1,1] ]
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack2(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack2(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    //48. Rotate Image  You are given an n x n 2D matrix representing an image. Rotate the image by 90 degrees (clockwise).
    public static void rotate(int[][] matrix) {
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        if (rowLen!=colLen) return;
        int[][] temp = new int[rowLen][colLen];
        for (int i = 0; i < rowLen ; i++) {
            for (int j = 0; j < colLen; j++) {
                temp[j][rowLen-i-1] = matrix[i][j];
            }
        }
        for (int i = 0; i < rowLen ; i++) {
            for (int j = 0; j < colLen; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    //49. Group Anagrams  Given an array of strings, group anagrams together.
    //Input: ["eat", "tea", "tan", "ate", "nat", "bat"] Output:
//            [["ate","eat","tea"],
//            ["nat","tan"],
//            ["bat"] ]
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List> map = new HashMap<>();
        for (String string: strs) {//遍历字符串数组
            char[] chars = string.toCharArray();//转化为字符数组
            Arrays.sort(chars);//排序
            String key = String.valueOf(chars);//保存排序后的字符串
            if (!map.containsKey(key)) map.put(key,new ArrayList());//判断map中是否包含当前字符串，不包含的话就添加进去
            map.get(key).add(string);//将当前字符串放入对应字符串的list中
        }
        return new ArrayList(map.values());
    }

    //50. Pow(x, n) Implement pow(x, n), which calculates x raised to the power n (xn).
    public static double myPow(double x, int n) {
        double ans= 1;
        for(long i = Math.abs((long)n); i > 0; i = i >> 1, x *=x)
            if((i & 1) == 1) ans *= x;
        return n > 0 ? ans : 1/ans;
    }
}
