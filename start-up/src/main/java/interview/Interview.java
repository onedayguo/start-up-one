package interview;

/**
 * @Description: 面试中被问到的问题
 * @Author: kami
 * @Date: 2021/4/14 15:42
 * @Version: 1.0.0
 */
public class Interview {

    /**
     * @description: 找到数组中最大的k个数字
     * @return: 数组
     * @author: kami
     * @备注： 快速排序改一下
     * @date: 2021/4/14 15:43
     */
    private int[] topK(int[] arr,int k){
        if (arr.length < k){
            return new int[0];
        }

        quickSelect(arr,k,0,arr.length-1);

        int[] topK = new int[k];
        for (int i = 0; i < k ; i++) {
            topK[i] = arr[i];
        }
        return topK;
    }
    /**
     * @discription 将数组中最大的K个数放在数组右侧
     * @date 2021/4/14 15:48
     **/
    private void quickSelect(int[] arr,int k,int low,int high){
        int leftNums = mergeTopK(arr,low,high);
        if (leftNums == k){
            return;
        }else if (leftNums > k){
            quickSelect(arr,k,low,leftNums-1);
        }else {
            quickSelect(arr,k,leftNums+1,high);
        }
    }
    /**
     * @discription 将数组中大于首位的放左边，小于首位的放右边
     * @date 2021/4/14 16:05
     **/
    private int mergeTopK(int[] arr,int low,int high){
        int curV = arr[low];
        int left = low;
        int right = high;
        while (left < right){
            while (left < right && arr[right] < curV){
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] > curV){
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = curV;
        return left;
    }

    private void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Interview main = new Interview();

        int[] arr = {5,4,2,3,9,8,7,1,6};
        int[] num = main.topK(arr,2);
        for (int i:num) {
            System.out.println(i);
        }
    }

}
