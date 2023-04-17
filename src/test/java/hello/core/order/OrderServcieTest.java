package hello.core.order;

import hello.core.section8.AppConfig;
import hello.core.section8.member.Grade;
import hello.core.section8.member.Member;
import hello.core.section8.member.MemberService;
import hello.core.section8.order.Order;
import hello.core.section8.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServcieTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
