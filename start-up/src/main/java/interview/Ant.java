package interview;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: 蚂蚁金服
 * @Author: kami
 * @Date: 2021/4/9 18:53
 * @Version: 1.0.0
 */
public class Ant {

//    给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；
//    否则返回 false 。
//
//    交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。
//    例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
//
//    例 A=ab, B=ab, return false
//    例 A=ab, B=ba, return true
//    例 A=aa, B=aa, return true
    public boolean similar(String A, String B){

        int aLen = A.length();
        int bLen = B.length();
        if(aLen != bLen){
            return false;
        }
        // A B 相同，找出A中有重复的字符即可
        if(A.equals(B)){
            Set<Character> set = new HashSet<>();
            for(int i=0;i<aLen;i++){
                if(set.contains(A.charAt(i))){
                    return true;
                }else{
                    set.add(A.charAt(i));
                }
            }
            return false;
        }else{
            // A B不同，找到两个不同点，比较A中两个不同点交换后是否与B相同；如果不同点多于2个则必然为false
            List<Integer> diff = new ArrayList<>();
            for(int i=0;i<aLen;i++){
                if(A.charAt(i) != B.charAt(i)){
                    diff.add(i);
                }
            }
            if(diff.size() == 2){
                boolean one = A.charAt(diff.get(0)) == (B.charAt(diff.get(1)));
                boolean two = A.charAt(diff.get(1)) == (B.charAt(diff.get(0)));
                return one && two;
            }
        }
        return false;
    }
}
