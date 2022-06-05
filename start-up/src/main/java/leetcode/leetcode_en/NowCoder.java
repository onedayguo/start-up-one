package leetcode.leetcode_en;

import java.util.Arrays;

/**
 * @Auther: kami
 * @Date: 2019/8/17 23:37
 * @Description: 来自牛客网的编程题，牛客网的题的参数需要从控制台手动输入
 */
public class NowCoder {
    public static void main(String[] args) {
        int[] arr = {5,4,2,5,6,9,8,7,1,5,6,3,4,10,2,5,61,23,18};

        int[] arr1 = Arrays.copyOf(arr,arr.length);
        bubbleSort(arr1);
        printArr(arr1);

        int[] arr2 = Arrays.copyOf(arr,arr.length);
        selectSort(arr2);
        printArr(arr2);

        int[] arr3 = Arrays.copyOf(arr,arr.length);
        quickSort(arr3,0,arr3.length-1);
        printArr(arr3);

        int[] arr4 = Arrays.copyOf(arr,arr.length);
        insertSort(arr4);
        printArr(arr4);

        int[] arr5 = Arrays.copyOf(arr,arr.length);
        shellSort(arr5);
        printArr(arr5);

        int[] arr6 = Arrays.copyOf(arr,arr.length);
        cutSort(arr6,0,arr6.length-1);
        printArr(arr6);
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

    /**
     * @discription 直接插入排序，一次将未排序的元素插入到已排序的序列中
     * @date 2019/8/18 20:01
     **/
    public static void insertSort(int[] arr){
        if (arr == null || arr.length == 0) return;
        else {
            int n = arr.length;
            for (int i = 1; i < n; i++) {
                int iKey = arr[i];
                int index = i;
                while (index > 0 && arr[index-1] > iKey){
                    arr[index] = arr[index-1];
                    index--;
                }
                if (index != i) arr[index] =iKey;
            }
        }
    }

    /**
     * @discription希尔排序，直接插入排序的升级版，分组进行插入排序
     * @date 2019/8/18 21:07
     **/
    public static void shellSort(int[] arr){
        if (arr == null || arr.length == 0) return;
        else {
            int n = arr.length;
            for (int step = n/2; step > 0 ; step /= 2) {
                for (int i = step; i < n; i++) {
                    int iKey = arr[i];
                    int index = i;
                    while (index-step >= 0 && arr[index-step] > iKey){
                        arr[index] = arr[index-step];
                        index -= step;
                    }
                    if (index != i) arr[index] = iKey;
                }
            }
        }
    }

    /**
     * @discription 归并排序，分为两步，1.拆分 2.合并
     * @date 2019/8/18 21:32
     **/
    public static void cutSort(int[] arr,int low,int high){
        if (arr == null || arr.length == 0 || arr.length == 1) return;
        else {
            int mid = (low+high)/2;
            if (low < high){
                cutSort(arr,low,mid);
                cutSort(arr,mid+1,high);
                merge(arr,low,mid,high);
            }
        }
    }
    public static void merge(int[] arr,int low,int mid,int high){
        int n = high-low+1;
        int[] tempArr = new int[n];
        int leftIndex = low;
        int rightIndex = mid+1;
        int tempArrIndex = 0;
        while (leftIndex <= mid && rightIndex <= high){
            if (arr[leftIndex] < arr[rightIndex]){
                tempArr[tempArrIndex++] = arr[leftIndex++];
            }else tempArr[tempArrIndex++] = arr[rightIndex++];
        }
        while (leftIndex <= mid) tempArr[tempArrIndex++] = arr[leftIndex++];
        while (rightIndex <= high) tempArr[tempArrIndex++] = arr[rightIndex++];
        for (int i = 0; i < n; i++) {
            arr[i+low] = tempArr[i];
        }
    }
}
