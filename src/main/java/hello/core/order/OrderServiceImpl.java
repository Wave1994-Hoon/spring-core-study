package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

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
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    //  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
