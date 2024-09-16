package refactoring.bookvillage.domain.member.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import refactoring.bookvillage.domain.member.entity.Member;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("멤버의 존재 유무를 알 수 있다.")
    void existsMemberByIdTest() {
        // given
        Member member = Member.createMember("email", "킴", "별명:쌩수", Member.MemberState.NEW, null);

        Member savedMember = memberRepository.save(member);

        // when
        boolean existsCreatedMember = memberRepository.existsMemberById(savedMember.getId());
        boolean existsNotCreatedMember = memberRepository.existsMemberById(20L);

        // then
        assertThat(existsCreatedMember).isTrue();
        assertThat(existsNotCreatedMember).isFalse();
    }

}