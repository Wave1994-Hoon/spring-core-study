package hello.core.singletonTest;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        /* 두 개가 같다. */
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        /*
        * bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$37362ce1
        * 순수한 클래스라면 class hello.core.AppConfig 처럼 출력되어야 한다.
        * 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서 AppCofig 클래스를 상속 받은 임의의 다른 클래스를 만들고 그 클래스를 빈으로 등록
        * 그 임의의 클래스가 바로 싱글톤을 보장하도록 해준다.
        *
        * CGLIB는 아마도 @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어짐
        * 덕분에 싱글톤 보장
        *
        * 만약 Configuration을 사용하지 않는다면 바이트코드를 조작하는 기술은 CGLIB 사용 안함 --> 싱글톤 보장 x
        * 스프링 설정 파일에는 @Configuration 꼭 사용
        */
        System.out.println("bean = " + bean.getClass());

    }
}
