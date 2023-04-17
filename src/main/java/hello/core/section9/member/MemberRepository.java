package hello.core.section9.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
