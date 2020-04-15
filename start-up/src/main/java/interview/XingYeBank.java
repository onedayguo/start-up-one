package interview;

import java.util.Scanner;

/**
 * @Description: 兴业银行赛马网笔试
 * @Auther: kami
 * @Date: 2020/4/15 07:55
 * @Version: 1.0.0
 */
public class XingYeBank {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String str = in.nextLine();
            replaceBlankSpace(str);
        }
    }

    private static void replaceBlankSpace(String str){
        String[] strArray = str.split(" ");
        int len = strArray.length;
        StringBuilder strRes = new StringBuilder();
        for (int i = 0; i < len-1; i++) {
            strRes.append(strArray[i]).append("%20");
        }
        strRes.append(strArray[len-1]);
        System.out.println(strRes);
    }

    private static void replaceBlankSpace2(String str){
        StringBuilder strRes = new StringBuilder();
        for (char chr:str.toCharArray()) {
            if (chr == ' '){
                strRes.append("%20");
            }else {
                strRes.append(chr);
            }
        }
        System.out.println(strRes);
    }
}
