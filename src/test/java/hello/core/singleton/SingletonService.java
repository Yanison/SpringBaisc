package hello.core.singleton;

public class SingletonService {
    /**
     * 자기 자신을 private static 으로 가지고 있다. -> class 레벨에 올라가기 떄문에 단 하나만 존재하게 됨.
     * static 존에 올리는 이유..?
     */
    private static final SingletonService instance = new SingletonService();
    public static SingletonService getInstance(){
        return instance;
    }

    // private Constructor 을 new 로 생성해서 외부에서 생성하지 못하도록 한다.
    private SingletonService(){}

    /**
     * 외부에서 SingletonService s = new SingletonService 를 하지 못하도록 막아놨음.
     */

    public void logic(){
        System.out.println("singletone");
    }
}
