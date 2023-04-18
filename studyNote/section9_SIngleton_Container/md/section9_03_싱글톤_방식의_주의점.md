# 싱글톤 방식의 주의점
- 싱글톤 패턴이든 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는 싱글톤 방식은 여러 클라이언트가, 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
- 무상태(stateless)로 설계해야 한다.
```
- 특정 클라이언트에 의존적인 필드가 있으면 안된다.
- 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
- 가급적 읽기만 가능해야 한다.
- 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
```
- 스프링 빈의 필드에 공유 값을 설정하면, 정말 큰 장애가 발생할 수 있다.


<Strong>상태를 유지할 경우 발생하는 문제점 예시</Strong>

```java

public class StatefulService {

    private int price; // 상태를 유지하려는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext();
        StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService",StatefulService.class);

        // Thread A : user A ordered price as 10000
        statefulService1.order("userA",10000);
        // Thread B: user B ordered price as 20000
        statefulService2.order("userB",20000);
        
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        /**
         * 현재 상황은 statefulService1의 주문 가격은 10000을 기대할 수 있다. 하지만
         * statefulService1와 statefulService2는 사실 공유된 자원을 공유하기 때문에 
         * statefulService1의 요청에 의해 할당된 자원이 statefulService2의 요청에 의해 덧 씌여지게 된다. 
         * 
         * 그러므로 아래의 테스트 코드의 isEqualTo(20000)는 실패하게 된다. 
         * 공유 필드에 접근하기 때문에 동시성 이슈가 생긴다. 공유자원에 접근할 때 항상 조심하자
         */

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
```

- 무상태로 설계해야 한다는 의미는 즉 공유되지 않는 지역번수 혹은 파라미터를 사용해야 한다.
```java

public class StatefulService {

    private int price; // 상태를 유지하려는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        //파라미터 사용해서 무상태로 설계
        return  price;
    }

    public int getPrice() {
        return price;
    }
}
```