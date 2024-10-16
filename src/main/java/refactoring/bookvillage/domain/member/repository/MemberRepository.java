package refactoring.bookvillage.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import refactoring.bookvillage.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {



}
