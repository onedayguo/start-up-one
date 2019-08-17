package leetcode.nowcoder;

/**
 * @Auther: kami
 * @Date: 2019/8/17 23:37
 * @Description: 来自牛客网的编程题，牛客网的题的参数需要从控制台手动输入
 */
public class NowCoder {
    public static void main(String[] args) {
        int[] arr = {5,4,2,5,6,9,8,7,1,5,6,3,4,10,2,5,61,23,18};
        bubbleSort(arr);

    }
    /**
     * @discription 冒泡排序
     * @date 2019/8/17 23:41
     **/
    public static void bubbleSort(int[] arr){
        if (arr == null || arr.length == 0) return;
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            boolean flag = true;
            for (int j = 0; j < len - i ; j++) {
                if (arr[j] > arr[j+1]){
                    flag = false;
                    arr[j] = arr[j] + arr[j+1];
                    arr[j+1] = arr[j] - arr[j+1];
                    arr[j] = arr[j] - arr[j+1];
                }
            }
            if (flag) break;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

}
