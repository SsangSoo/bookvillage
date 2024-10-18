package refactoring.bookvillage.domain.borrowcomment.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.CreateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.UpdateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.entity.BorrowComment;
import refactoring.bookvillage.domain.borrowcomment.repository.BorrowCommentRepository;
import refactoring.bookvillage.domain.borrowcomment.service.dto.UpdateBorrowCommentDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BorrowCommentServiceImplTest {

    @Autowired
    BorrowCommentService borrowCommentService;

    @Autowired
    BorrowCommentRepository borrowCommentRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        borrowRepository.deleteAllInBatch();
        memberRepository.deleteAllInBatch();
        borrowCommentRepository.deleteAllInBatch();
    }


    @Test
    @DisplayName("대여 게시글 댓글을 작성할 수 있다.")
    void createBorrowCommentTest() {
        //given
        Long memberId = 1L;
        Long borrowId = 23L;
        CreateBorrowCommentRequest createBorrowCommentRequest = getCreateBorrowCommentRequest("댓글을 달다!");

        //when
        Long borrowCommentId = borrowCommentService.create(createBorrowCommentRequest.requestToServiceDto(memberId, borrowId));
        BorrowComment borrowComment = borrowCommentRepository.findById(borrowCommentId).orElseThrow();

        //then
        assertThat(borrowComment)
                .extracting("comment", "memberId", "borrowId")
                .containsExactlyInAnyOrder("댓글을 달다!", 1L, 23L);
    }

    @Test
    @DisplayName("대여 게시글 댓글을 수정할 수 있다.")
    void borrowCommentUpdateTest() {
        // given
        Member savedMember = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        Borrow savedBorrow = borrowRepository.save(Borrow.createBorrow(getCreateBorrowDto(savedMember.getId())));

        CreateBorrowCommentRequest createBorrowCommentRequest = getCreateBorrowCommentRequest("댓글을 달다!");
        Long borrowCommentId = borrowCommentService.create(createBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId()));

        UpdateBorrowCommentRequest updateBorrowCommentRequest = new UpdateBorrowCommentRequest("댓글 수정");
        UpdateBorrowCommentDto updateBorrowCommentDto = updateBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId(), borrowCommentId);

        //when
        borrowCommentService.update(updateBorrowCommentDto);
        BorrowComment borrowComment = borrowCommentRepository.findById(borrowCommentId).orElseThrow();

        // then
        assertThat(borrowComment).extracting("comment", "memberId", "borrowId")
                .containsExactlyInAnyOrder(savedMember.getId(), savedBorrow.getId(), updateBorrowCommentDto.getComment());
    }


    static CreateBorrowCommentRequest getCreateBorrowCommentRequest(String comment) {
        return new CreateBorrowCommentRequest(comment);
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