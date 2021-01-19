package hello.component;

import hello.component.member.Grade;
import hello.component.member.Member;
import hello.component.member.MemberService;
import hello.component.order.Order;
import hello.component.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

        /* 이전의 appConfig 와 같은 역할을 함*/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"itemA", 10000);
        System.out.println("order = " + order.toString());
    }
}
