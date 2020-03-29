package interview;

import com.sun.deploy.util.StringUtils;

import java.util.*;

/**
 * @Auther: kami
 * @Date: 2019/9/11 18:08
 * @Description:
 */
public class HuaWei {

//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        //1.
//        while (in.hasNext()){
//            String input = in.nextLine().trim();
//            int aStartIndex = 3;
//            int aEndIndex = input.indexOf('B') - 3;
//            int bStartIndex = aEndIndex + 6;
//            int bEndIndex = input.length() - 6;
//            int rIndex = input.length() - 1;
//            int aLength = (aEndIndex - aStartIndex +1)/2 + 1;
//            int bLength = (bEndIndex - bStartIndex +1)/2 +1;
//            int r = input.charAt(rIndex) - '0';
//            int[] a = new int[aLength];
//            int[] b = new int[bLength];
//            for (int i = 0; i < aLength ; i++) {
//                a[i] = input.charAt(aStartIndex) - '0';
//                aStartIndex += 2;
//                if ( aStartIndex > aEndIndex) {
//                    break;
//                }
//            }
//            for (int i = 0; i < bLength ; i++) {
//                b[i] = input.charAt(bStartIndex) - '0';
//                bStartIndex += 2;
//                if ( bStartIndex > bEndIndex) {
//                    break;
//                }
//            }
//            pairAB(a,b,r);
//
//        }
//        //3.
//        while (in.hasNext()){
//            int n = in.nextInt();
//            String[] oldInfos = new String[n];
//            for (int i = 0; i < n; i++) {
//                oldInfos[i] = in.nextLine();
//            }
//            int m = in.nextInt();
//            String[] nexInfos = new String[m];
//            for (int i = 0; i < m; i++) {
//                nexInfos[i] = in.nextLine();
//            }
//
//        }
//    }
    //1.
    public static void pairAB(int[] a,int[] b,int r){
        StringBuilder res = new StringBuilder();
        int aLength = a.length;
        int bLength = b.length;
        for (int i = 0; i < aLength; i++) {
            Boolean hasAdded = false;
            for (int j = 0; j < bLength; j++) {
                int dif = b[j] - a[i];
                if (0 <= dif && dif <= r){
                    res.append("(").append(a[i]).append(",").append(b[j]).append(")");
                    hasAdded = true;
                    continue;
                }else if (!hasAdded && dif > r){
                    res.append("(").append(a[i]).append(",").append(b[j]).append(")");
                    break;
                }
            }
        }
        System.out.println(res);
    }
    //2.
    public static void reverseStr(String input){
        Stack<String> stack = new Stack<>();
        String[] strArray = input.split(" ");
        int length = strArray.length;
        for (int i = 0; i < length; i++) {
            String item = strArray[i];

        }
    }

    //3.
    public static void newDistribute(String[] oldInfos,String[] newInfos){

    }

    /**
     * @description: 计算字符串最后一个单词的长度，单词以空格隔开
     * 一行字符串，非空，长度小于5000。   整数N，最后一个单词的长度。
     * @return:
     * @auther: kami
     * @date: 2020/3/21 22:06
     */
    public static int lengthOfLastWord(String words){
        int length = words.length();
        int lenOfLastWord = 0;
        for (int i = length-1; i >= 0 ; i--) {
            if (' ' == words.charAt(i)){
                return lenOfLastWord;
            }
            lenOfLastWord++;
        }
        return lenOfLastWord;
    }

    public static int countLetter(String word,String letter){
        int count = 0;
        int length = word.length();
        for (int i = 0; i < length; i++) {
            if (letter.equalsIgnoreCase(String.valueOf(word.charAt(i)))){
                count++;
            }

        }
        return count;
    }

    /**
     * @description: 牛客-解码
     * 小Q想要给他的朋友发送一个神秘字符串，但是他发现字符串的过于长了，于是小Q发明了一种压缩算法对字符串中重复的部分进行了压缩，
     *对于字符串中连续的m个相同字符串S将会压缩为[m|S](m为一个整数且1<=m<=100)，例如字符串ABCABCABC将会被压缩为[3|ABC]，
     * 现在小Q的同学收到了小Q发送过来的字符串，你能帮助他进行解压缩么？
     * @return:
     * @auther: kami
     * @date: 2020/3/29 15:15
     */
    public static String decode(String words){
        while (words.contains("]")){
            int right = words.indexOf("]");
            int left = words.lastIndexOf("[", right);
            String repeatStr = words.substring(left+1, right);
            String[] split = repeatStr.split("\\|");
            words = words.replace("["+repeatStr+"]",
                    String.join("", Collections.nCopies(Integer.parseInt(split[0]), split[1])));
        }
        return words;
    }









    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int a = in.nextInt();
            allPremution(a);
        }
    }


    private static void allPremution(int n){
        List<List<Integer>> res = new ArrayList<>();
        dfs(res,n,new ArrayList<>());
        for (List<Integer> out:res) {
            for (Integer in:out) {
                System.out.print(in);
            }
        }
    }
    private static void dfs(List<List<Integer>> lists,int n,List<Integer> tempList){
        if (tempList.size() == n){
            lists.add(new ArrayList<>(tempList));
        }else {
            for (int i = 1; i <= n; i++) {
                if (!tempList.contains(i)){
                    tempList.add(i);
                    dfs(lists,n,tempList);
                    tempList.remove(tempList.size()-1);
                }
            }
        }
    }
}
