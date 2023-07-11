package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

//Test는 메소드 순서 상관없이 실행된다.
//그래서 @AfterEach 어노테이션 없이는 이미 저장된 값을 불러올 수 있기 떄문에, clear해주는 게 좋음
public class MemoryMemberRepositoryTest {   //굳이 public 안해도 됨

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test   //Test 어노테이션을 사용하여 테스트
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //System.out.println("result = " + (result == member));   //이렇게 눈으로 볼 수 있게 하는데, 이건 실무에서 x 항상 볼 수는 없으니 -> assert기능을 쓴다

        Assertions.assertEquals(member, result);    //asert jupiter api
        assertThat(member).isEqualTo(result);   //assertj
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  //shift + f6 하면 rename됨
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        Member result2 = repository.findByName("spring2").get();

        assertThat(result1).isEqualTo(member1);
        assertThat(result2).isEqualTo(member2);
    }

    @Test
    public void findALl() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring1");
        repository.save(member2);

        List<Member> result = repository.findALL();

        assertThat(result.size()).isEqualTo(2);
    }
}
