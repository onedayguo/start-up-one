package leetcode.interest;

/**
 * @Description: 验证代码执行顺序
 * @Auther: kami
 * @Date: 2020/5/5 11:04
 * @Version: 1.0.0
 */
public class Client {
    public static void main(String[] args) {
        Father father = new Chlid();
    }
}

class Father {
    {
        System.out.println("3 Father's block");
    }
    static {
        System.out.println("1 Father's static block");
    }
}

class Chlid extends Father {
    {
        System.out.println("4 Chlid's block");
    }
    static {
        System.out.println("2 Chlid's static block");
    }
}

