package leetcode.interest;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: 借鉴他人思想，算法题
 * @Auther: kami
 * @Date: 2020/4/18 09:12
 * @Version: 1.0.0
 */
public class CopyThought {

    /**
     * @description: 1.合并区间
     * 给出一个区间的集合，合并其中重叠的区间
     * @return: 去重后的集合
     * @auther: kami
     * @date: 2020/4/18 9:14
     */
    public int[][] mergeRange(int[][] array){
        Arrays.sort(array,new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[][] ans = new int[array.length][2];
        int count = -1;
        for (int[] boundary:array) {
            if (count == -1 || boundary[0] > ans[count][1]){
                ans[++count] = boundary;
            }else {
                ans[count][1] = Math.max(ans[count][1],boundary[1]);
            }
        }
        return ans;
    }
}
