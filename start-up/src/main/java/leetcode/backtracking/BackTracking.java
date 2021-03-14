package leetcode.backtracking;

import java.util.*;

/**
 * @Description: 回朔法
 * @Auther: kami
 * @Date: 2020/3/29 10:16
 * @Version: 1.0.0
 */
public class BackTracking {
    /**
     * @description: 784. Letter Case Permutation
     * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
     * Return a list of all possible strings we could create.
     * @return:
     * @auther: kami
     * @date: 2020/3/29 10:16
     */
    public List<String> letterCasePermutationBFS(String S) {
        if (S == null) {
            return new LinkedList<>();
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(S);
        int length = S.length();
        for (int i = 0; i < length; i++) {
            if (Character.isLetter(S.charAt(i))) {
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    char[] chars = queue.poll().toCharArray();

                    chars[i] = Character.toUpperCase(chars[i]);
                    queue.offer(String.valueOf(chars));

                    chars[i] = Character.toLowerCase(chars[i]);
                    queue.offer(String.valueOf(chars));
                }
            }
        }
        return new LinkedList<>(queue);
    }

    /**
     * @description: 784. Letter Case Permutation
     * @return:
     * @auther: kami
     * @date: 2020/3/29 10:44
     */
    public List<String> letterCasePermutationDFS(String S) {
        if (S == null) {
            return new LinkedList<>();
        }

        List<String> res = new LinkedList<>();
        //借助一个帮助函数
        helper(S.toCharArray(), res, 0);
        return res;
    }

    public void helper(char[] chs, List<String> res, int pos) {
        //首先判断长度是否足够，其实这个地方不用判断，直接添加也可以？
        if (pos == chs.length) {
            res.add(new String(chs));
            return;
        }
        //如果当前字符时数字，则跳到下个字符
        if (chs[pos] >= '0' && chs[pos] <= '9') {
            helper(chs, res, pos + 1);
            return;
        }

        //转化为小写，从这个入口进去的是一个以当前字母为小写的字符串的组合
        chs[pos] = Character.toLowerCase(chs[pos]);
        helper(chs, res, pos + 1);

        ////转化为大写，从这个入口进去的是一个以当前字母为大写的字符串的组合
        chs[pos] = Character.toUpperCase(chs[pos]);
        helper(chs, res, pos + 1);
    }

    /**
     * @description: 401. Binary Watch
     * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
     * Each LED represents a zero or one, with the least significant bit on the right.
     * 时 8   4   2   1 （1-11）
     * 分 32 16 8 4 2 1 （0-59）
     * 特殊点： 11:00,0:00,11:59，0:59
     * @return:
     * @auther: kami
     * @date: 2020/3/29 10:48
     */
    public List<String> readBinaryWatch(int num) {
        //Just go through the possible times and collect those with the correct number of one-bits.
        List<String> res = new ArrayList<>();
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                //because the biggest number of minutes is 59, which is "111011", 6 bit, so need to left move h to 6 bit which is h<<6 or h*64
                if (Integer.bitCount(h * 64 + m) == num){
                    res.add(String.format("%d:%02d",h,m));
                }
            }
        }
        return res;

    }
    String[][] hour = {{"0"},
            {"1", "2", "4", "8"},
            {"3", "5", "6", "9", "10"},
            {"7", "11"}};
    String[][] minute = {{"00"},
            {"01", "02", "04", "08", "16", "32"},
            {"03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48"},
            {"07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49", "50", "52", "56"},
            {"15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58"},
            {"31", "47", "55", "59"}};

    public List<String> readBinaryWatch1(int num) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i <= 3 && i <= num; i++) {
            if (num - i <= 5) {
                for (String str1 : hour[i]) {
                    for (String str2 : minute[num - i]) {
                        ret.add(str1 + ":" + str2);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * @description: 1079. Letter Tile Possibilities
     * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible
     * non-empty sequences of letters you can make.
     * @return: 全排列个数
     * @auther: kami
     * @date: 2020/3/29 11:32
     */
    public int numTilePossibilities(String tiles) {
        int[] count = new int[26];
        for (char ch:tiles.toCharArray()) {
            count[ch-'A']++;
        }
        return dfs(count);


    }
    private int dfs(int[] count){
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0){
                sum++;
                count[i]--;
                sum += dfs(count);
                count[i]++;
            }
        }
        return sum;
    }

    /**
     * @description: 93. Restore IP Addresses
     * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
     * 1921562536
     * @return: ip地址的集合
     * @auther: kami
     * @date: 2020/3/30 21:54
     */
    public List<String> restoreIpAddresses(String s) {
        int length = s.length();
        if (length < 4 || length >12) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 4 && i < length-2 ; i++) {
            for (int j = i+1; j < i+4 && j < length-1; j++) {
                for (int k = j+1; k <j+4 && k < length; k++) {
                    String s1 = s.substring(0,i),s2 = s.substring(i,j),s3 = s.substring(j,k),s4 = s.substring(k,length);
                    if (validate(s1) && validate(s2) && validate(s3) && validate(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
    public boolean validate(String s){
        if (s.length() > 3 || s.length() == 0 || (s.charAt(0) == '0' && s.length()>1) || Integer.parseInt(s) > 255) return false;
        return true;
    }

    /**
     * @description: 126. Word Ladder II
     *Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
     *  from beginWord to endWord, such that:
     * Only one letter can be changed at a time
     * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     * Note:
     * Return an empty list if there is no such transformation sequence.
     * All words have the same length.
     * All words contain only lowercase alphabetic characters.
     * You may assume no duplicates in the word list.
     * You may assume beginWord and endWord are non-empty and are not the same.
     * @return:
     * @auther: kami
     * @date: 2020/3/31 22:16
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(beginWord) || !wordList.contains(endWord)) return new ArrayList<>();
        int len = wordList.size();
        for (int i = 0; i < len; i++) {
            
        }
        return null;
    }

    private void backTrackFindLadders(List<List<String>> res,List<String> wordList,String currentWord,int nextIndex,String endWord,
                                      List<String> tempList){
        if (tempList.contains(endWord)){
            res.add(new ArrayList<>(tempList));
        }else {
            String nextWord = wordList.get(nextIndex);
            if (oneBitDiff(currentWord,nextWord)){
                tempList.add(nextWord);
                for (int i = 0; i < 10; i++) {
                    
                }
                backTrackFindLadders(res,wordList,nextWord,nextIndex+1,endWord,tempList);
                tempList.remove(nextIndex);
            }
        }


    }

    private boolean oneBitDiff(String a,String b){
        int len = a.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (count > 1){
                return false;
            }else {
                if (a.charAt(i) != b.charAt(i)){
                    count++;
                }
            }
        }
        return true;
    }
    /**
     * @description: 1688. Count of Matches in Tournament
     * You are given an integer n, the number of teams in a tournament that has strange rules:
     * If the current number of teams is even, each team gets paired with another team. A total of n / 2 matches
     * are played, and n / 2 teams advance to the next round.
     * If the current number of teams is odd, one team randomly advances in the tournament, and the rest gets paired.
     * A total of (n - 1) / 2 matches are played, and (n - 1) / 2 + 1 teams advance to the next round.
     * Return the number of matches played in the tournament until a winner is decided.
     * @return: 匹配队伍的数量
     * @author: kami
     * @备注：TODO
     * @date: 2021/3/14 12:04
     */
    public static int numberOfMatches(int n) {
        if (n == 1){
            return 0;
        }
        int match = n / 2;
        if (n % 2 != 0){
            return match+numberOfMatches(match+1);
        }else {
            return match+numberOfMatches(match);
        }
    }
    /**
     * @description: 797. All Paths From Source to Target
     * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0
     * to node n - 1, and return them in any order.
     *
     * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
     * (i.e., there is a directed edge from node i to node graph[i][j]).
     * @return: 从起点到终点的所有路径
     * @author: kami
     * @备注： 回朔法
     * @date: 2021/3/14 12:23
     */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        cur.add(0);
        backTrackPath(graph,0,res,cur);
        return res;
    }
    private void backTrackPath(int[][] graph, int curValue, List<List<Integer>> res, List<Integer> cur){
        if (curValue == graph.length-1){
            res.add(new ArrayList<>(cur));
            return;
        }else {
            for (int value:graph[curValue]) {
                cur.add(value);
                backTrackPath(graph,value,res,cur);
                cur.remove(cur.size()-1);
            }
        }
    }
    /**
     * @description: 1286. Iterator for Combination
     * Design the CombinationIterator class:
     * CombinationIterator(string characters, int combinationLength) Initializes the object with a string
     * characters of sorted distinct lowercase English letters and a number combinationLength as arguments.
     * next() Returns the next combination of length combinationLength in lexicographical order.
     * hasNext() Returns true if and only if there exists a next combination.
     * @return: 联合迭代器
     * @author: kami
     * @备注： 好难
     * @date: 2021/3/14 13:05
     */
    static class CombinationIterator {

        List<String> list;
        int index = 0;
        public CombinationIterator(String characters, int combinationLength) {
            list = new ArrayList<>();
            generateSeq(characters,"", 0, 0, combinationLength);
            Collections.sort(list);
        }
        void generateSeq(String characters, String current, int iterIndex, int count, int cLen) {
            if(count == cLen) {
                list.add(current);
                return;
            }
            if(iterIndex == characters.length()) {
                return;
            }
            // 包含当前字符
            generateSeq(characters, current + characters.charAt(iterIndex), iterIndex + 1, count + 1, cLen);
            // 跳过当前字符
            generateSeq(characters, current, iterIndex + 1, count, cLen);
        }

        public String next() {
            String res = list.get(index);
            index++;
            return res;
        }

        public boolean hasNext() {
            if(index == list.size()) {
                return false;
            }
            return true;
        }
    }
    /**
     * @description: 1415. The k-th Lexicographical String of All Happy Strings of Length n
     * A happy string is a string that:
     *
     * consists only of letters of the set ['a', 'b', 'c'].
     * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
     * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa",
     * "baa" and "ababbc" are not happy strings.
     *
     * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical
     * order.
     *
     * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
     * @return: 返回第K个长度为N的自然排序的字符串，或者空串
     * @author: kami
     * @备注：TODO
     * @date: 2021/3/14 20:56
     */
    private static final char[] CHARS = new char[] { 'a', 'b', 'c' };
    public String getHappyString(int n, int k) {
        StringBuilder sb = new StringBuilder();
        build(n, '-', k, sb); // 放置一个虚拟头字符，按照普通字符处理abc
        return sb.toString();
    }

    private static void build(int n, char last, int k, StringBuilder sb) {
        if (n == 0) {
            return; // 到达叶子
        }
        int step = 1 << (n - 1); // 分支的节点数量，完全二叉树
        int to = step;
        for (char c : CHARS) {
            if (c == last) {
                continue; // 相邻节点不相等
            }
            if (k <= to) {
                build(n - 1, c, k - (to - step), sb.append(c)); // get the child tree and deduct the number of
                // elements in left branch from k, if `to` is the right boundary of current branch, then (to-step) is
                // the right boundary of the left branch, which is the number of all elements in it
                return;
            }
            to += step;
        }
    }

    public static void main(String[] args) {
        System.out.println(numberOfMatches(7));
    }

}

