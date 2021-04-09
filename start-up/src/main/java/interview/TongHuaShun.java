package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 同花顺面试
 * @Auther: kami
 * @Date: 2020/5/18 17:02
 * @Version: 1.0.0
 */
public class TongHuaShun {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6,7,9,8};
//        int[] arr2 = {7,9,10,21};
//        int[] res = mergeArray(arr1,arr2);
//        for (int i = 0,len=res.length; i < len; i++) {
//            System.out.print(res[i]+ " ");
//        }

        printAllMidNum(arr1);
    }

    /**
     * @description: 合并两个有序的数组，并去重
     * @return: 合并后的数组
     * @auther: kami
     * @date: 2020/5/18 17:43
     */
    private static int[] mergeArray(int[] arr1,int[] arr2){
        if (arr1 == null || arr2 == null){
            return null;
        }
        int len1 = arr1.length;
        int len2 = arr2.length;
        int resLen = len1+len2;
        int[] resArr = new int[resLen];
        int index1 = 0;
        int index2 = 0;
        int resIndex = 0;
        while (index1 < len1 && index2 < len2){
            if (arr1[index1] < arr2[index2]){
                resArr[resIndex++] = arr1[index1++];
            }else if (arr1[index1] > arr2[index2]){
                resArr[resIndex++] = arr2[index2++];
            }else {
                resArr[resIndex++] = arr1[index1++];
                index2++;
            }
        }
        while (index1 < len1){
            resArr[resIndex++] = arr1[index1++];
        }
        while (index2 < len2){
            resArr[resIndex++] = arr2[index2++];
        }
        return resArr;
    }
    /**
     * @description: 随机int数组，找到所有数，左边小，右边大
     * @return: TODO
     * @author: kami
     * @备注：TODO
     * @date: 2021/4/9 14:52
     */
    public static void printAllMidNum(int[] nums){
        int[] leftMax = new int[nums.length];
        int leftCurMax = nums[0];
        int[] rightMin = new int[nums.length];
        int rightCurMin = nums[nums.length-1];
        List<Integer> res = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            leftCurMax = Math.max(leftCurMax,nums[i]);
            leftMax[i] = leftCurMax;
            rightCurMin = Math.min(rightCurMin,nums[nums.length-i-1]);
            rightMin[nums.length-i-1] = rightCurMin;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= leftMax[i] && nums[i] <= rightMin[i]){
                res.add(nums[i]);
            }
        }
        res.forEach(System.out::println);
    }
}
