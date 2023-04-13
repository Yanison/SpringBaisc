package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * test commit check
 * Configuration 애플리케이션의 설정정보임을 명시해주는 어노테이션
 * @Bean 스프링 컨테이너에 등록
 *
 * @Configuration 을 사용하면 CGLIB을 사용해서 이미 등록한 빈이 있으면
 * 호출해서 반환한다. 그렇기 때문에 스프링 컨테이너가 싱글톤을 보장할 수 있었던 것
 */
@Configuration
public class AppConfig {
    /**
     * 스프링을 사용하면 스프링이 대신 감독을 해준다.
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
}
