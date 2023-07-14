package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링 부트 Test할 때
@Transactional

class MemberServiceIntegrationTest {

    @Autowired  MemberService memberService;
    @Autowired
    MemberRepository memoryRepository;

    @Test
    void 회원가입() {   //Test 메서드는 한글로 적기도 함. 실제 코드에 포함되지 않아서 괜찮다.
        //given 주어짐
        Member member = new Member();
        member.setName("hello");

        //when 이거 실행했을 때
        Long saveId = memberService.join(member);

        //then 이게 나와야 한다
        Member findMember = memberService.findOne(saveId).get();
        //Assertions에 갖다대면 static으로 가능
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }
}