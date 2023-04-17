package hello.core.section8;

import hello.core.section8.member.Grade;
import hello.core.section8.member.Member;
import hello.core.section8.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        /**
         * AppConfig에서 등록한 Bean은 메소드 이름이 key, return이 value로 컨테이너에 저장이 됨
         * 그렇기 때문에 꺼낼때는 getBean("key",type)으로 꺼내면 된다.
         * %% type은 해당 key값, 즉 bean으로 등록된 메소드의 타입이다.
         */
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);

        Member member = new Member(1L,"memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
