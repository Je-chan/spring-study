package com.springstudy.springcorepractice.order;

import com.springstudy.springcorepractice.discount.DiscountPolicy;
import com.springstudy.springcorepractice.discount.RateDiscountPolicy;
import com.springstudy.springcorepractice.member.MemberService;
import com.springstudy.springcorepractice.member.MemberServiceImpl;
import com.springstudy.springcorepractice.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig 는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다
// 또한 AppConfig 는 생성한 객체 인스턴스의 레퍼런스를 생성자를 통해서 주입해준다.
    // MemberServiceImpl 에는 MemoryMemberRepository를
    // OrderServiceImpl 에는 MemoryMemberRepository와 FixDiscountPolicy를
    // 만약 Discount 정책을 RateDiscountPolicy로 바꾼다고 하면 여기서 바꿔주면 될 것.
    // 클라이언트 코드에는 따로 로직 수정을 할 필요가 없어진다
// AppConfig 는 공연 기획자. 구체 클래스를 선택한다(배역에 맞는 배우를 선택한다)
// 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다

//public class AppConfig {
//    // 객체의 생성과 연결을 담당하게 된다
//        // MemberServiceImpl 로 객체를 생성하고
//        // MemoryMemberRepository 와 연결을 한다
//    // 이렇게 해주는 게 관심사를 분리하는 것. 즉, 객체를 생성하고 연결하는 역할과 실행하는 역할이 분리된 것
//    // 이를 한 마디로 표현하면 Dependency Injection = DI, 의존관계 주입이라고 한다
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    private MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    // 기획이 바뀐다 해도 여기서만 코드를 변경해주면 된다.
//    // 로직을 사용하는 사용 영역에는 코드를 건드리지 않고
//    // 로직을 구성하는 구성 영역에서만 코드를 건드리면 되는 것
//    public DiscountPolicy discountPolicy() {
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }


// 지금부터 Spring 으로 만들어 보자
@Configuration
public class AppConfig {

    // @Bean 을 넣으면 다들 스프링 컨테이너에 등록된다
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
