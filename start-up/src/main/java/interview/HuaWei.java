package interview;

import java.util.Scanner;
import java.util.Stack;

/**
 * @Auther: kami
 * @Date: 2019/9/11 18:08
 * @Description:
 */
public class HuaWei {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //1.
        while (in.hasNext()){
            String input = in.nextLine().trim();
            int aStartIndex = 3;
            int aEndIndex = input.indexOf('B') - 3;
            int bStartIndex = aEndIndex + 6;
            int bEndIndex = input.length() - 6;
            int rIndex = input.length() - 1;
            int aLength = (aEndIndex - aStartIndex +1)/2 + 1;
            int bLength = (bEndIndex - bStartIndex +1)/2 +1;
            int r = input.charAt(rIndex) - '0';
            int[] a = new int[aLength];
            int[] b = new int[bLength];
            for (int i = 0; i < aLength ; i++) {
                a[i] = input.charAt(aStartIndex) - '0';
                aStartIndex += 2;
                if ( aStartIndex > aEndIndex) {
                    break;
                }
            }
            for (int i = 0; i < bLength ; i++) {
                b[i] = input.charAt(bStartIndex) - '0';
                bStartIndex += 2;
                if ( bStartIndex > bEndIndex) {
                    break;
                }
            }
            pairAB(a,b,r);

        }
        //3.
        while (in.hasNext()){
            int n = in.nextInt();
            String[] oldInfos = new String[n];
            for (int i = 0; i < n; i++) {
                oldInfos[i] = in.nextLine();
            }
            int m = in.nextInt();
            String[] nexInfos = new String[m];
            for (int i = 0; i < m; i++) {
                nexInfos[i] = in.nextLine();
            }

        }
    }
    //1.
    public static void pairAB(int[] a,int[] b,int r){
        StringBuilder res = new StringBuilder();
        int aLength = a.length;
        int bLength = b.length;
        for (int i = 0; i < aLength; i++) {
            Boolean hasAdded = false;
            for (int j = 0; j < bLength; j++) {
                int dif = b[j] - a[i];
                if (0 <= dif && dif <= r){
                    res.append("(").append(a[i]).append(",").append(b[j]).append(")");
                    hasAdded = true;
                    continue;
                }else if (!hasAdded && dif > r){
                    res.append("(").append(a[i]).append(",").append(b[j]).append(")");
                    break;
                }
            }
        }
        System.out.println(res);
    }
    //2.
    public static void reverseStr(String input){
        Stack<String> stack = new Stack<>();
        String[] strArray = input.split(" ");
        int length = strArray.length;
        for (int i = 0; i < length; i++) {
            String item = strArray[i];

        }
    }

    //3.
    public static void newDistribute(String[] oldInfos,String[] newInfos){

    }

}
