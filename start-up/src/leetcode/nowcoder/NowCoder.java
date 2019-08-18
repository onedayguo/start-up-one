package leetcode.nowcoder;

/**
 * @Auther: kami
 * @Date: 2019/8/17 23:37
 * @Description: 来自牛客网的编程题，牛客网的题的参数需要从控制台手动输入
 */
public class NowCoder {
    public static void main(String[] args) {
        int[] arr1 = {5,4,2,5,6,9,8,7,1,5,6,3,4,10,2,5,61,23,18};
        int[] arr2 = {5,4,2,5,6,9,8,7,1,5,6,3,4,10,2,5,61,23,18};
        int[] arr3 = {5,4,2,5,6,9,8,7,1,5,6,3,4,10,2,5,61,23,18};

        bubbleSort(arr1);
        printArr(arr1);

        selectSort(arr2);
        printArr(arr2);

        quickSort(arr3,0,arr3.length-1);
        printArr(arr3);

    }

    /**
     * @discription 冒泡排序，两两比较，大数后移
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

    }

    /**
     * @discription 选择排序，每次选择未排序序列的最小值
     * @date 2019/8/18 10:43
     **/
    public static void selectSort(int[] arr){
        if (arr == null || arr.length == 0) return;
        else {
            int n = arr.length;
            for (int i = 0; i < n-1; i++) {
                int min = arr[i];
                int minIndex = i;
                for (int j = i+1; j < n; j++) {
                    if (arr[j] < min){
                        minIndex = j;
                        min = arr[j];
                    }
                }
                if (i != minIndex){
                    arr[minIndex] = arr[i];
                    arr[i] = min;
                }

            }
        }
    }

    /**
     * @discription 快速排序
     * @date 2019/8/18 11:26
     **/
    public static void quickSort(int[] arr,int start,int end){
        if (start < end){
            int left = start;
            int right = end;
            int key = arr[start];
            while (left < right){
                while (left < right && arr[right] > key) right--;
                if (left < right) arr[left++] = arr[right];
                while (left < right && arr[left] < key) left++;
                if (left < right) arr[right--] = arr[left];
            }
            arr[left] = key;
            quickSort(arr,start,left-1);
            quickSort(arr,left+1,end);
        }
    }

    /**
     * @discription 打印数组
     * @date 2019/8/18 11:30
     **/
    public static void printArr(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

}
