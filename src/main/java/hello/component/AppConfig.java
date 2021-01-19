package hello.component;

import hello.component.discount.DiscountPolicy;
import hello.component.discount.RateDiscountPolicy;
import hello.component.member.MemberService;
import hello.component.member.MemberServiceImpl;
import hello.component.member.MemoryMemberRepository;
import hello.component.order.OrderService;
import hello.component.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration  // application 설정 정보
public class AppConfig {

    /*
    * memberRepository 가 한 번만 생성됨
    */

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
