package hello.core.section8.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
