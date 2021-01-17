package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;


public class OrderServiceImpl implements OrderService{
    /*
     * OrderServiceImpl은 DiscountPolicy 인터페이스 뿐만 아니라 FixDiscountPolicy 인터페이스 구현체 클래스도 함께 의존
     * ---> DIP 위반
     *
     * FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스코드도 함께 변경되어야 한다.
     * 클라이언트의 소스 코드도 변경 됨
     * ---> OCP 위반
     *
     * 만약 인터페이스에만 의존하도록 설계와 코드를 변경한다면 아래와 같이 작성할 수 있다.
     * private DiscountPolicy discountPolicy
     * ---> 하지만 호출하는 순간 NPE 발생
     *
     * 해결방안
     * 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어여 한다.
     */
    //  private final MemberRepository memberRepository = new MemoryMemberRepository();
    //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();


    /*
    * 정적인 객체 인스턴스 관계
    * 인터페이스에만 의존, 구현체에 대한 정보 모름
    */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    * 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다. (단지, DiscountPolicy 인터페이스에만 의존)
    * `OrderServiceImpl` 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입) 알 수 없다.
    * `OrderServiceImpl`의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부에 있는 AppConfig에 의해 결정된다.
    * `OrderServiceImpl`은 이제부터 실행에만 집중하게 된다. (역할을 고르지 않아도됨)
    *  */

    /* 동적인 객체 인스턴스 관계 */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // for test
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

