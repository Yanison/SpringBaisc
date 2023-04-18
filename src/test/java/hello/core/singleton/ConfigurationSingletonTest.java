package hello.core.singleton;

import hello.core.section9.AppConfig;
import hello.core.section9.member.MemberRepository;
import hello.core.section9.member.MemberServiceImpl;
import hello.core.section9.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void cofigurationTest(){
//        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
//        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
//        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
//
//        MemberRepository memberRepository1 = memberService.getMemberRepository();
//        MemberRepository memberRepository2 = orderService.getMemberRepository();
//
//
//        System.out.println("memberService -> memberRepository =" + memberRepository1);
//        System.out.println("orderService -> memberRepository =" + memberRepository2);
//        System.out.println("memberRepository = " + memberRepository);
//
////        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
////        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

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
