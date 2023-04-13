# XML 로 Bean 등록하고 관리하기


### appConfig.xml
```
<bean id="memberService" class="hello.core.member.MemberServiceImpl" >
    <constructor-arg name="memberRepository" ref="memberRepository"/>
</bean>

<bean id="memberRepository" class="hello.core.member.MemoryMemberRepository"/>

<bean id="orderService" class="hello.core.order.OrderServiceImpl">
    <constructor-arg name="memberRepository" ref="memberRepository"/>
    <constructor-arg name="discountPolicy" ref="discountPolicy"/>
</bean>

<bean id="discountPolicy" class="hello.core.discount.RateDiscountPolicy"/>
```

### AppConfig.class
```
@Bean
public MemberService memberServry();
}
@Bean
public OrderService orderService(){
    return new OrderServiceImpl(memberRepository(),discountPolicy());
}
@Bean
public DiscountPolicy discountPolicy(){
    return new FixDiscountPolicy();
}
```

- xml 기반 설정 정보 'appConfig.xml'과 자바 코드로 된 스프링 설정 정보 'AppConfig.java' 설정 정보를 비교해보면 거의 비슷하다는 것을 알 수 있다.
- xml 기반으로 설정하는 것은 최근에 잘 사용하지 않는다. 자세한건 스프링 공식 레퍼런스 문서 참고ice() {
    return new MemberServiceImpl(memberRepository());
}
@Bean
private static MemberRepository memberRepository() {
    return new MemoryMemberReposito고