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
    private static MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    
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
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
```