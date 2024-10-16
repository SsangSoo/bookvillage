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
    @DisplayName("삭제 플래그를 통해 멤버의 삭제 유무를 식별한다.")
    void deleteMembersByIdTest() {
        // given
        Member member = Member.createMember("email", "킴", "별명:쌩수", Member.MemberState.NEW, null);
        Member savedMember = memberRepository.save(member);

        // when
        boolean existsCreatedMember = savedMember.isDeleteTag();

        //then
        assertThat(existsCreatedMember).isFalse();
    }
}