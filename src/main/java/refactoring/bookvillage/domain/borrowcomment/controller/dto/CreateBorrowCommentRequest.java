package refactoring.bookvillage.domain.borrowcomment.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import refactoring.bookvillage.domain.borrowcomment.service.dto.CreateBorrowCommentDto;

@AllArgsConstructor
public class CreateBorrowCommentRequest {

    @NotBlank(message = "댓글을 작성해주세요.")
    private String comment;

    public CreateBorrowCommentDto requestToServiceDto(Long memberId, Long borrowId) {
        return CreateBorrowCommentDto.builder()
                .comment(comment)
                .borrowId(borrowId)
                .memberId(memberId)
                .build();
    }

}
