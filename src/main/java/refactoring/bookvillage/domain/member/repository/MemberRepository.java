package refactoring.bookvillage.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.bookvillage.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsMemberById(Long memberId);
}
