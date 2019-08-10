package rpc.server;

public class ServiceImpl implements HelloService {
    @Override
    public String sayHi(String name) {
        return "hi "+name;
    }
}
