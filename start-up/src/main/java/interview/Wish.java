package interview;

import java.util.Calendar;
import java.util.Date;

public class Wish {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        //时间，可以为具体的某一时间
        Date today = new Date();
        calendar.setTime(today);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        int monthDay = calendar.get(Calendar.DAY_OF_MONTH);
        int yearDay = calendar.get(Calendar.DAY_OF_YEAR);
        if (weekDay == 1) {
            weekDay = 7;
        } else {
            weekDay = weekDay - 1;
        }
        System.out.println("当前时间是，本周的第" + weekDay  + "天");
        System.out.println("当前时间是，本月的第" + monthDay + "天");
        System.out.println("当前时间是，本年的第" + yearDay  + "天");
    }
}
