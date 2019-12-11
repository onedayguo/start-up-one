package advance.proxy;

public class Main {
    public static void main(String[] args) {
        Star ldh = new LiuDeHua();

        StarProxy proxy = new StarProxy();

        proxy.setTarget(ldh);

        Object obj = proxy.createProxyObj();

        Star star = (Star)obj;
        star.dance("街舞");
    }
}
