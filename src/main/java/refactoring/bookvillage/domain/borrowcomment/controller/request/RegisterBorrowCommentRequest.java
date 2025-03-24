package refactoring.bookvillage.domain.borrowcomment.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import refactoring.bookvillage.domain.borrow.service.dto.commentdto.CreateBorrowCommentDto;

@AllArgsConstructor
public class RegisterBorrowCommentRequest {

    @NotBlank(message = "댓글을 작성해주세요.")
    private String comment;

    public CreateBorrowCommentDto toServiceRequest(Long memberId, Long borrowId) {
        return CreateBorrowCommentDto.builder()
                .comment(comment)
                .borrowId(borrowId)
                .memberId(memberId)
                .build();
    }

}
