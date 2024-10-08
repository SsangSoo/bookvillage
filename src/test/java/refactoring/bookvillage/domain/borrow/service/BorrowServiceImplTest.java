package refactoring.bookvillage.domain.borrow.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.controller.dto.UpdateBorrowRequest;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
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
        memberRepository.deleteAllInBatch();
        borrowRepository.deleteAllInBatch();
    }


    @Test
    @DisplayName("책 대여 게시글을 생성할 수 있다.")
    void createBorrowTest() {
        // given
        Member member = Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null);
        Member savedMember = memberRepository.save(member);

        assertThat(member.getId()).isEqualTo(savedMember.getId());
        CreateBorrowDto createBorrowDto = getCreateBorrowDto(savedMember.getId());

        // when
        borrowService.create(createBorrowDto);
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowDto.getTitle(), createBorrowDto.getBookTitle());

        // then
        assertThat(findBorrow.getTitle()).isEqualTo(createBorrowDto.getTitle());
        assertThat(findBorrow.getBookTitle()).isEqualTo(createBorrowDto.getBookTitle());
        assertThat(findBorrow.getContent()).isEqualTo(createBorrowDto.getContent());

    }

    @Test
    @DisplayName("책 대여 게시글을 수정할 수 있다.")
    void updateBorrowTest() {
        // given
        Member savedMember = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        CreateBorrowDto createBorrowDto = getCreateBorrowDto(savedMember.getId());

        borrowService.create(createBorrowDto);
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowDto.getTitle(), createBorrowDto.getBookTitle());

        UpdateBorrowRequest updateRequestDto = getUpdateRequestDto();

        // when
        borrowService.update(updateRequestDto.updateRequestToServiceDto(), findBorrow.getId(), savedMember.getId());
        Borrow findUpdatedBorrow = borrowRepository.findBorrowByTitleAndBookTitle(updateRequestDto.getTitle(), updateRequestDto.getBookTitle());

        // then
        assertThat(findUpdatedBorrow.getTitle()).isEqualTo(updateRequestDto.getTitle());
        assertThat(findUpdatedBorrow.getBookTitle()).isEqualTo(updateRequestDto.getBookTitle());
        assertThat(findUpdatedBorrow.getContent()).isEqualTo(updateRequestDto.getContent());
    }

    @Test
    @DisplayName("작성자 이외의 사람은 수정을 시도할 수 없다.")
    void updateBorrowTestByOtherWriter() {
        // given
        Member ssangsoo = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        Member soo = memberRepository.save(Member.createMember("ss@eamil.com", "강", "수", Member.MemberState.NEW, null));
        CreateBorrowDto createBorrowDto = getCreateBorrowDto(ssangsoo.getId());

        borrowService.create(createBorrowDto);
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowDto.getTitle(), createBorrowDto.getBookTitle());

        UpdateBorrowRequest updateRequestDto = getUpdateRequestDto();

        // when-then
        assertThatThrownBy(() -> borrowService.update(updateRequestDto.updateRequestToServiceDto(), findBorrow.getId(), soo.getId()))
                .isInstanceOf(BusinessException.class)
                .hasMessage("작성자 외 회원이 접근 중입니다");
    }


    @Test
    @DisplayName("책 대여 게시글을 삭제할 수 있다.")
    void deleteBorrowTest() {
        // given
        Member savedMember = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        CreateBorrowDto createBorrowDto = getCreateBorrowDto(savedMember.getId());

        borrowService.create(createBorrowDto);
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowDto.getTitle(), createBorrowDto.getBookTitle());

        UpdateBorrowRequest updateRequestDto = getUpdateRequestDto();

        // when
        borrowService.delete(findBorrow.getId(), savedMember.getId());
        Borrow findUpdatedBorrow = borrowRepository.findBorrowByTitleAndBookTitle(updateRequestDto.getTitle(), updateRequestDto.getBookTitle());

        // then
        assertThat(findBorrow.isDeleteTag()).isTrue();
    }



    @Test
    @DisplayName("작성자 이외의 사람은 삭제를 시도할 수 없다.")
    void deleteBorrowTestByOtherWriter() {
        // given
        Member ssangsoo = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        Member soo = memberRepository.save(Member.createMember("ss@eamil.com", "강", "수", Member.MemberState.NEW, null));
        CreateBorrowDto createBorrowDto = getCreateBorrowDto(ssangsoo.getId());

        borrowService.create(createBorrowDto);
        Borrow findBorrow = borrowRepository.findBorrowByTitleAndBookTitle(createBorrowDto.getTitle(), createBorrowDto.getBookTitle());

        // when-then
        assertThatThrownBy(() -> borrowService.delete(findBorrow.getId(), soo.getId()))
                .isInstanceOf(BusinessException.class)
                .hasMessage("작성자 외 회원이 접근 중입니다");
    }

//    @Test
//    @DisplayName("존재하지 않는 ")
//
//




    private UpdateBorrowRequest getUpdateRequestDto() {
        return new UpdateBorrowRequest("변경된 책 제목",
                "책 빌려드립니다.",
                "DDD",
                "여전히 에릭 에반스",
                "한빛? 에이콘이었나..?",
                null);
    }

    private CreateBorrowDto getCreateBorrowDto(Long memberId) {
        return CreateBorrowDto.builder()
                .title("책 제목")
                .content("책 빌려드립니다.")
                .author("에릭 에반스")
                .bookTitle("DDD")
                .publisher("한빛? 에이콘이었나..?")
                .thumbnail(null)
                .memberId(memberId)
                .build();
    }

}