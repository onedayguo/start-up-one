package interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 满帮
 * @Author: kami
 * @Date: 2021/5/14 10:13
 * @Version: 1.0.0
 */
public class ManBang {
    /**
     * @description: 数组中寻找目标和为target的两个数的下标
     * @return: 下标数组
     * @author: kami
     * @关键词： map
     * @date: 2021/5/14 10:45
     */
    private int[] twoSum(int[] nums,int target){
        if (nums == null || nums.length == 0){
            return new int[0];
        }
        Map<Integer,Integer> existNum = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int expect = target - nums[i];
            if (existNum.containsKey(expect)){
                int[] result = new int[2];
                result[0] = existNum.get(expect);
                result[1] = i;
                return result;
            }else {
                existNum.put(nums[i],i);
            }
        }
        return new int[0];
    }
}
