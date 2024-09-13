package refactoring.bookvillage.domain.borrow.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class BorrowServiceImplTest {

    @Autowired
    BorrowService borrowService;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("책 대여 게시글 생성시 memberId를 찾을 수 없으면 예외가 발생한다.")
    void createBorrowThrowExceptionIfMemberMissing() {
        // given-when-then
        assertThatThrownBy(() -> borrowService.createBorrow(null, 1L))
                .isInstanceOf(BusinessException.class)
                .hasMessage("멤버가 존재하지 않습니다.");
    }

    @Test
    @DisplayName("책 대여 게시글 생성 테스트")
    void createBorrowTest() {

    }



}