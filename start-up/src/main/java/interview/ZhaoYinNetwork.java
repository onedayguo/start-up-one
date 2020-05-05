package interview;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Description: 招银网络
 * @Auther: kami
 * @Date: 2020/5/5 13:57
 * @Version: 1.0.0
 */
public class ZhaoYinNetwork {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();

        }
    }

    /**
     * @description: 招银网络编程题
     * target是目标值，找到所有连续数字，其和为target，找到所有的这样的序列
     * @return:
     * @auther: kami
     * @date: 2020/5/5 16:35
     */
    private List<List<Integer>> FindContinuousSequence(int target){

        //存放结果
        List<List<Integer>> result = new ArrayList<>();
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int plow = 1,phigh = 2;
        while(phigh > plow){
            //由于是连续的，差为1的一个序列，那么求和公式是(a0+an)*n/2
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            //相等，那么就将窗口范围的所有数添加进结果集
            if(cur == target){
                List<Integer> list = new ArrayList<>();
                for(int i=plow;i<=phigh;i++){
                    list.add(i);
                }
                result.add(list);
                plow++;
                //如果当前窗口内的值之和小于sum，那么右边窗口右移一下
            }else if(cur < target){
                phigh++;
            }else{
                //如果当前窗口内的值之和大于sum，那么左边窗口右移一下
                plow++;
            }
        }
        return result;
    }
}
