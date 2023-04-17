package hello.core.section8.order;

import hello.core.section8.discount.DiscountPolicy;
import hello.core.section8.member.Member;
import hello.core.section8.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    /**
     * OrderServiceImpl의 입장에서 생성자를 통해 들어올 구현체가 어떤 구현체인지는 알 수 없다.
     * 다만 AppConfig에서 결정할 뿐이다.
     *
     * 때문에 OrderServiceImpl는 의존관계에 대한 고민은 외부에 맡기고 오직 로직 실행에만 집중 하면 된다.
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
