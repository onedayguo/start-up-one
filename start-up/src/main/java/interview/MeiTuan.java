package interview;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @Description: 美团
 * @Auther: kami
 * @Date: 2020/3/26 17:05
 * @Version: 1.0.0
 */
public class MeiTuan {
    final static int mod = 998244353;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String keynumber = in.nextLine();
            String numbers = in.nextLine();
            sumPreLine(keynumber,numbers);
        }
    }

    /**
     * @description: 套娃前缀和
     * @return: array[K][N] 和
     * @auther: kami
     * @date: 2020/3/26 20:50
     */
    public static void sumPreLine(String keyNumber,String arrayStr){
        int N = keyNumber.charAt(0) - '0';
        int K = keyNumber.charAt(2) - '0';
        String[] array1 = arrayStr.split("");
        int[] preArray = new int[N];
        for (int i = 0; i < N; i++) {
            preArray[i] = Integer.parseInt(array1[i]);
        }
        int sum = 0;
        for (int i = 0; i < K; i++) {
            int[] currentArray = new int[N];
            for (int j = 0; j < N; j++) {
                int currentPre = j > 0 ? currentArray[j-1] : 0;
                currentArray[j] = currentPre + preArray[i];
                sum += currentArray[j] % mod;
            }
        }
        System.out.print(sum);

    }

    /**
     * @description: 子序列
     * @return:
     * @auther: kami
     * @date: 2020/3/26 20:33
     */
    public static void countSub(int count,String sub){
        String[] subArray = sub.split(" ");
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < count+1; i++) {
            int value = Integer.parseInt(subArray[i-1]);
            if (value % i == 0){
                list.add(value);
            }
        }

    }

    /**
     * @description: 计算黑色圆环的面积
     * @return:
     * @auther: kami
     * @date: 2020/3/26 19:40
     */
    public static void calcArea(String count1,String numbers){
        int count = Integer.parseInt(count1);
        String[] rayStrings = numbers.split(" ");
        int[] rayArray = new int[rayStrings.length];
        for (int i = 0; i < count; i++) {
            rayArray[i] = Integer.parseInt(rayStrings[i]);
        }
        Arrays.sort(rayArray);
        int sum = 0;
        int flag = 1;
        for (int i = 0; i < count; i++) {
            sum += (flag*Math.pow(rayArray[i],2));
            flag *= -1;
        }
        BigDecimal res = new BigDecimal(Math.PI * sum).setScale(5, RoundingMode.HALF_UP);
        System.out.println(res);
    }

    /**
     * @description: 转换数字，第i个整数表述将数字i替换为a[i]
     * @return:
     * @auther: kami
     * @date: 2020/3/26 19:12
     */
    public static void convertNumber(String number,String index){
        String[] numberStrArr = number.split("");
        String[] indexNum = index.split(" ");
        Map<Integer,String> map = new HashMap<>();
        for (int i = 1; i < indexNum.length+1; i++) {
            //第i个，第1个从0开始算
            map.put(i,indexNum[i-1]);
        }
        for (int i = 0; i < numberStrArr.length; i++) {
            int con = numberStrArr[i].charAt(0)-'0';
            if (map.containsKey(con)){
                numberStrArr[i] = map.get(con);
            }
        }
        for (String str:numberStrArr) {
            System.out.println(str);
        }
    }


}
