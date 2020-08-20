package rule.general;

/**
 * @Auther: kami
 * @Date: 2019/12/7 20:50
 * @Description:
 */
public class GeneralRule {
    public static void main(String[] args) {

    }

   /**
    * @description: 容易混淆的long类型标志，l是小写字母，与数字1易混淆，建议使用大写字母L
    * @return:
    * @auther: kami
    * @date: 2019/12/7 21:18
    */
    private static void easyToMixError(){
        long i = 1L;
        System.out.println("i 的两倍是："+(i+i));
    }

    /**
     * @description: 运行期才确定的常量，不建议这样定义
     * @return:
     * @auther: kami
     * @date: 2019/12/7 21:22
     */
    private static void changeConst(){
        System.out.println("常量会变哦："+Const.RAND_CONST);
    }
}
