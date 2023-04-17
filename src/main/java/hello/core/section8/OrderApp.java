package hello.core.section8;

import hello.core.section8.member.Grade;
import hello.core.section8.member.Member;
import hello.core.section8.member.MemberService;
import hello.core.section8.order.Order;
import hello.core.section8.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService =  appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService",OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId,"table",30000);

        System.out.println("order = " + order);
        System.out.println("order = " + order.calculatePrice());
    }
}
