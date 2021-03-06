package hello.core.member;


public class MemberServiceImpl implements MemberService {

    /* 인터페이스와 구현체 모두 의존 */
    // private final MemberRepository memberRepository = new MemoryMemberRepository(); // command shift enter

    /* 추상화에만 의존   */
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

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
