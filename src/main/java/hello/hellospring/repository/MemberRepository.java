package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //멤버 변수를 저장하는 거긴 한데.. 이게 뭐고
    Optional<Member> findById(Long id); //findByid로 null 값을 가져오게 되었을 때 optional로 필터
    Optional<Member> findByName(String name);
    List<Member> findALL();
}
