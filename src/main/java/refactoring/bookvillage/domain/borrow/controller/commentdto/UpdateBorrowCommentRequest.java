package refactoring.bookvillage.domain.borrow.controller.commentdto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import refactoring.bookvillage.domain.borrow.service.dto.commentdto.UpdateBorrowCommentDto;

@AllArgsConstructor
public class UpdateBorrowCommentRequest {
    @NotBlank(message = "댓글을 작성해주세요.")
    private String comment;

    public UpdateBorrowCommentDto requestToServiceDto(Long memberId, Long borrowId, Long borrowCommentId) {
        return UpdateBorrowCommentDto.builder()
                .comment(comment)
                .borrowId(borrowId)
                .memberId(memberId)
                .borrowCommentId(borrowCommentId)
                .build();
    }
}
