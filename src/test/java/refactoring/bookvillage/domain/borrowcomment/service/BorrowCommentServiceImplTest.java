package refactoring.bookvillage.domain.borrowcomment.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import refactoring.bookvillage.domain.borrowcomment.controller.dto.CreateBorrowCommentRequest;
import refactoring.bookvillage.domain.borrowcomment.entity.BorrowComment;
import refactoring.bookvillage.domain.borrowcomment.repository.BorrowCommentRepository;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class BorrowCommentServiceImplTest {

    @Autowired
    BorrowCommentService borrowCommentService;

    @Autowired
    BorrowCommentRepository borrowCommentRepository;

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
                .extracting("content", "memberId", "borrowId")
                .containsExactlyInAnyOrder("댓글을 달다!", 1L, 23L);
    }


    static CreateBorrowCommentRequest getCreateBorrowCommentRequest(String comment) {
        return new CreateBorrowCommentRequest(comment);
    }

}