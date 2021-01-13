package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/*
* 애플리케이션의 실제 동작에 필요한 구현 객체 생성
* 생성한 객체 인스터슨의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해준다.
* appConfig 객체는 `memoryMemberRepository` 객체르 생성하고 그 참조 값을 `memberServiceImpl`을 생성하면서 생성자로 전달한다.
* 클라이언트인 `memberServiceImpl` 입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 Dependency Injection 이라고 한다.
*/
public class AppConfig {

    /* 생성자 주입 */
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /* 생성자 주입 */
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
