# @Configuration과 싱글톤

아래의 AppConfig 코드를 보자. 과연 각각의 메소드가 얼마나 호출이 될까?
```java
@Configuration
public class AppConfig {
    /**
     * 아래의 메소드들이 어떻게 진행될지 시나리오를 생각해보자.
     * 1. @Bean memberService()를 호출하게 되면  -> memberRepository() 메소드도 호출이 된다.
     * 2. memberRepository() 메소드가 호출이 되면 MemoryMemberRepository() 객체가 생성이 된다.
     * 3. @Bean orderService()를 호출하게 되면 -> memberRepository() 메소드도 호출이 된다. :: 여기까지 memberRepository()가 2번 호출이 됐다.
     * 이렇게 되면 결과적으로 MemoryMemberRepository() 객체가 2번 생성되는 것인데, 이는 싱글톤이 깨지는것 처럼 보인다.
     */
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    /**
     * 주의! @Conficuration 과 Bean 조합으로 싱글톤을 보장하는 경우는 정적이지 않은 메소드일때 이다. 
     * 정적 메서드에 @Bean을 사용하게 되면 싱글톤 보장을 위한 지원을 받지 못한다.
     * 
     * By marking this method as static, <br>
     * it can be invoked without causing instantiation of its declaring @Configuration class, <br>
     * thus avoiding the above-mentioned lifecycle conflicts.  <br>
     * Note however that static @Bean methods will not be enhanced  <br>
     * for scoping and AOP semantics as mentioned above <br>
     * 
     * static 메소드로 선언하면 @Configuration을 클래스의 인스턴스 없이 호출할 수 있고 라이프 사이클의 충돌도 방지할 수 있다. 
     * <br> 하지만 static @Bean 메소드는 스코핑이나 AOP를 개선시키지 못한다.
     * 
     * https://docs.spring.io/spring-framework/docs/6.0.x/javadoc-api/org/springframework/context/annotation/Bean.html
     * 
     * @Bean
        private static MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
        }
     */
    
    
    @Bean
    public OrderService orderService(){

        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        System.out.println("call AppConfig.discountPolicy");
        return new RateDiscountPolicy();
    }
}
```


## 결과를 테스트 해보자

```java
public class ConfigurationSingletonTest {

    @Test
    void cofigurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        System.out.println("memberService -> memberRepository =" + memberRepository1);
        System.out.println("memberService -> memberRepository =" + memberRepository2);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
}
```

- memoryRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다. 
- AppConfig의 자바 코드를 보면 분명히 각각 2번 "new MemoryMemberRepository()" 가 호출되었는데, 실제로는 1번만 호출되었다.

