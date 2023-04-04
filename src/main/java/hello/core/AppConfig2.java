package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig2 {
    /**
     * 아래의 코드들을 보면 각 서비스마다의 역할들이 보이고
     * 열할들에 대한 구현들도 잘 나타난다.
     *
     * 즉, 배우의 교체는 배우가 직접 하지 않고 AppConfig가 대신한다.
     * 배우는 연기에 집중하고 AppConfig는 감독에만 집중하는 것.
     */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
