package refactoring.bookvillage.domain.borrow.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.CreateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequestDto;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class BorrowServiceImplTest {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        borrowRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
    }

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
        // given
        Member member = Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null);
        Member savedMember = memberRepository.save(member);

        assertThat(member.getId()).isEqualTo(savedMember.getId());
        CreateBorrowRequestDto createBorrowRequestDto = getCreateRequestDto();

        // when
        borrowService.createBorrow(createBorrowRequestDto, member.getId());
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowRequestDto.getTitle(), createBorrowRequestDto.getBookTitle());

        // then
        assertThat(findBorrow.getTitle()).isEqualTo(createBorrowRequestDto.getTitle());
        assertThat(findBorrow.getBookTitle()).isEqualTo(createBorrowRequestDto.getBookTitle());
        assertThat(findBorrow.getContent()).isEqualTo(createBorrowRequestDto.getContent());

    }

    @Test
    @DisplayName("책 대여 게시글 생성 후 수정 테스트")
    void updateBorrowTest() {
        // given
        Member member = Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null);
        Member savedMember = memberRepository.save(member);

        assertThat(member.getId()).isEqualTo(savedMember.getId());
        CreateBorrowRequestDto createBorrowRequestDto = getCreateRequestDto();

        borrowService.createBorrow(createBorrowRequestDto, member.getId());
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowRequestDto.getTitle(), createBorrowRequestDto.getBookTitle());

        UpdateBorrowRequestDto updateRequestDto = getUpdateRequestDto();

        // when
        borrowService.updateBorrow(updateRequestDto, findBorrow.getId(), member.getId());
        Borrow findUpdatedBorrow = borrowRepository.findBorrowByTitleAndBookTitle(updateRequestDto.getTitle(), updateRequestDto.getBookTitle());

        // then
        assertThat(findUpdatedBorrow.getTitle()).isEqualTo(updateRequestDto.getTitle());
        assertThat(findUpdatedBorrow.getBookTitle()).isEqualTo(updateRequestDto.getBookTitle());
        assertThat(findUpdatedBorrow.getContent()).isEqualTo(updateRequestDto.getContent());
    }



    private UpdateBorrowRequestDto getUpdateRequestDto() {
        return new UpdateBorrowRequestDto("변경된 책 제목",
                "책 빌려드립니다.",
                "DDD",
                "여전히 에릭 에반스",
                "한빛? 에이콘이었나..?",
                null);
    }

    private CreateBorrowRequestDto getCreateRequestDto() {
        return new CreateBorrowRequestDto("책 제목",
                "책 빌려드립니다.",
                "DDD",
                "에릭 에반스",
                "한빛? 에이콘이었나..?",
                null);
    }


}