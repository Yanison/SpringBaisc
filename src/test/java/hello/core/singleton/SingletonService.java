package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    // private Constructor 을 new 로 생성해서 외부에서 생성하지 못하도록 한다.
    private SingletonService(){}

    public static void main(String[] args) {

    }

    public void logic(){
        System.out.println("singletone");
    }
}
