package refactoring.bookvillage.domain.borrowcomment.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import refactoring.bookvillage.domain.borrow.service.command.BorrowCommentCommandService;
import refactoring.bookvillage.domain.borrow.entity.Borrow;
import refactoring.bookvillage.domain.borrow.repository.BorrowRepository;
import refactoring.bookvillage.domain.borrow.service.dto.borrowdto.CreateBorrowDto;
import refactoring.bookvillage.domain.borrow.controller.commentdto.BorrowCommentResponse;
import refactoring.bookvillage.domain.borrow.controller.commentdto.CreateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrow.controller.commentdto.UpdateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrow.entity.BorrowComment;
import refactoring.bookvillage.domain.borrow.repository.BorrowCommentRepository;
import refactoring.bookvillage.domain.borrow.service.dto.commentdto.UpdateBorrowCommentDto;
import refactoring.bookvillage.domain.member.entity.Member;
import refactoring.bookvillage.domain.member.repository.MemberRepository;
import refactoring.bookvillage.global.exception.BusinessException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class BorrowCommentServiceImplTest {

    @Autowired
    BorrowCommentCommandService borrowCommentService;

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
        Member savedMember = memberRepository.save(Member.createMember("email", "킴", "별명:쌩수", Member.MemberState.NEW, null));
        Borrow savedBorrow = borrowRepository.save(Borrow.createBorrow(getCreateBorrowDto(savedMember.getId())));

        CreateBorrowCommentRequest createBorrowCommentRequest = getCreateBorrowCommentRequest("댓글을 달다!");

        //when
        BorrowCommentResponse borrowCommentResponse = borrowCommentService.create(createBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId()));
        BorrowComment borrowComment = borrowCommentRepository.findById(savedBorrow.getId()).orElseThrow();

        //then
        assertThat(borrowComment)
                .extracting("comment", "memberId", "borrowId")
                .containsExactlyInAnyOrder("댓글을 달다!", savedMember.getId(), savedBorrow.getId());
    }

    @Test
    @DisplayName("대여 게시글 댓글을 수정할 수 있다.")
    void borrowCommentUpdateTest() {
        // given
        Member savedMember = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        Borrow savedBorrow = borrowRepository.save(Borrow.createBorrow(getCreateBorrowDto(savedMember.getId())));

        CreateBorrowCommentRequest createBorrowCommentRequest = getCreateBorrowCommentRequest("댓글을 달다!");
        BorrowCommentResponse borrowCommentResponse = borrowCommentService.create(createBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId()));

        UpdateBorrowCommentRequest updateBorrowCommentRequest = new UpdateBorrowCommentRequest("댓글 수정");
        UpdateBorrowCommentDto updateBorrowCommentDto = updateBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId(), borrowCommentResponse.getId());

        //when
        borrowCommentService.update(updateBorrowCommentDto);
        BorrowComment borrowComment = borrowCommentRepository.findById(borrowCommentResponse.getId()).orElseThrow();

        // then
        assertThat(borrowComment).extracting("comment", "memberId", "borrowId")
                .containsExactlyInAnyOrder(savedMember.getId(), savedBorrow.getId(), updateBorrowCommentDto.getComment());
    }

    @Test
    @DisplayName("대여 게시글 댓글을 삭제할 수 있다.")
    void borrowCommentDeleteTest() {
        // given
        Member savedMember = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        Borrow savedBorrow = borrowRepository.save(Borrow.createBorrow(getCreateBorrowDto(savedMember.getId())));

        CreateBorrowCommentRequest createBorrowCommentRequest = getCreateBorrowCommentRequest("댓글을 달다!");
        BorrowCommentResponse borrowCommentResponse = borrowCommentService.create(createBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId()));


        //when
        borrowCommentService.delete(savedBorrow.getId(), borrowCommentResponse.getId(), savedMember.getId());
        BorrowComment borrowComment = borrowCommentRepository.findById(borrowCommentResponse.getId()).orElseThrow();

        //then
        assertThat(borrowComment.isDeleteTag()).isTrue();
    }

    @Test
    @DisplayName("대여 게시글 댓글을 삭제한 상태에서 또 삭제를 할 수 없다.")
    void borrowCommentDoubleDeleteTest() {
        // given
        Member savedMember = memberRepository.save(Member.createMember("ss@eamil.com", "킴", "쌩수", Member.MemberState.NEW, null));
        Borrow savedBorrow = borrowRepository.save(Borrow.createBorrow(getCreateBorrowDto(savedMember.getId())));

        CreateBorrowCommentRequest createBorrowCommentRequest = getCreateBorrowCommentRequest("댓글을 달다!");
        BorrowCommentResponse borrowCommentResponse = borrowCommentService.create(createBorrowCommentRequest.requestToServiceDto(savedMember.getId(), savedBorrow.getId()));

        borrowCommentService.delete(savedBorrow.getId(), borrowCommentResponse.getId(), savedMember.getId());

        //when, then
        assertThatThrownBy(() -> borrowCommentService.delete(savedBorrow.getId(), borrowCommentResponse.getId(), savedMember.getId()))
                .hasMessage("이미 삭제된 댓글입니다.")
                .isInstanceOf(BusinessException.class);

        BorrowComment borrowComment = borrowCommentRepository.findById(borrowCommentResponse.getId()).orElseThrow();

        assertThat(borrowComment.isDeleteTag()).isTrue();

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