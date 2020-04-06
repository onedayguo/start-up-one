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

}
