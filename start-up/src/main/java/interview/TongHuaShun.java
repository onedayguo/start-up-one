package interview;

/**
 * @Description: 同花顺面试
 * @Auther: kami
 * @Date: 2020/5/18 17:02
 * @Version: 1.0.0
 */
public class TongHuaShun {

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6,7};
        int[] arr2 = {7,9,10,21};
        int[] res = mergeArray(arr1,arr2);
        for (int i = 0,len=res.length; i < len; i++) {
            System.out.print(res[i]+ " ");
        }
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
}
