package interview;

import java.util.ArrayList;
import java.util.List;

public class Cuhk {
    //1.字符压缩编码
    /**
     * Given a string with less than 10^4 characters, encode its substring as "nX" where  'X'  is
     * the  only  character.  For  example,  "ETTTTTEEESLL"  would  been coded as "5T4ES2L".
     */
//    public String encodeSubstring(String s){
//        int len = s.length();
//        if (s == null || len ==0 ) return s;
//        Map<Character,Integer> map = new HashMap<>();
//        for (int i = 0; i < len ; i++) {
//
//        }
//    }

    //2.Given n, please output 1/n (1<=n<=10^5) for the first repetend.
    public List<Double> repetend(int[] input){
        List<Double> list = new ArrayList<>();
        if (input == null || input.length == 0) return list;
        for (int i = 0; i < input.length ; i++) {
            if (input[i] != 0) list.add(1.00D/input[i]);
            else list.add(Double.MAX_VALUE);
        }
        return list;
    }
    //3.Please write a function to tell if string A is a substring of another string B. (not use 'in' )
    public boolean isSubstring(String A,String B){
        if (A.isEmpty() || B.isEmpty()) return false;
        int aLen = A.length(),bLen = B.length();
        if (aLen > bLen) return false;
        for (int i = 0; i < bLen - aLen +1 ; i++) {
            int tempI = i;
            int startB = 0;
            while (B.charAt(tempI) == A.charAt(startB)){
                if (startB == bLen -1) return true;
                else {
                    tempI++;
                    startB++;
                }
            }
        }
        return false;
    }
    //4.Given randp() function. This function outputs 1 with a probability of p and outputs 0 with a
    // probability of 1-p. Use randp() to write a new function to produce 0 / 1 both with a 50% probability.

    //5.Given a list of non negative integers, arrange them such that they form the largest number.
    //**Sample Input** [3,30,34,5,9]   **Sample Output** 9534330


}
