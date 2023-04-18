package hello.core.section9.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    @Autowired // @Component를 쓰면 autowired를 쓰게 된다. ac.getBean(MemberRepository.class)와 같다.
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스 트
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
