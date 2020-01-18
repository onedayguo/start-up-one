package advance.proxy;

public class LiuDeHua implements Star {
    @Override
    public String sing(String name) {
        System.out.println("给我一杯忘情水");
        return "刘德华唱完";
    }

    @Override
    public String dance(String name) {
        System.out.println("开心的跳舞");
        return "刘德华跳完";
    }
}
