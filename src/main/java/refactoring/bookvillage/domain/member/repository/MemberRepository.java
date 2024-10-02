package refactoring.bookvillage.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import refactoring.bookvillage.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m.deleteTag from Member m where m.id = :memberId")
    boolean existsMemberById(Long memberId);
}
