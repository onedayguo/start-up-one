package interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Scanner;

public class Tencent implements Serializable {
    public static void main(String[] args) throws IOException {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("请输入一个字符串： ");

        Scanner in = new Scanner(System.in);
        System.out.println("请再输入一个字符串： ");
        while (in.hasNext()){
            System.out.println("请输入一个数字，输入10退出");
            int num = in.nextInt();
            System.out.println("你输入的数字为："+num);
            if (num == 10) break;
        }

        //System.out.println("line1的值为：" + line1);
    }
}
