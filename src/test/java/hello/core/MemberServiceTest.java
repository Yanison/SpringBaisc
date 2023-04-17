package hello.core;

import hello.core.section9.AppConfig;
import hello.core.section9.member.Grade;
import hello.core.section9.member.Member;
import hello.core.section9.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    @Test
    void join(){
        //given
        Member member = new Member(1L,"member", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);// assertThat(member) == isEqualTo(findMember) 둘이 같아?

    }
}
