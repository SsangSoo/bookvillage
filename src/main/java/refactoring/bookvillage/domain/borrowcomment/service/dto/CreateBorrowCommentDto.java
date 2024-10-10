package refactoring.bookvillage.domain.borrowcomment.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateBorrowCommentDto {
    private String comment;
    private Long borrowId;
    private Long memberId;

    @Builder
    private CreateBorrowCommentDto(String comment, Long borrowId, Long memberId) {
        this.comment = comment;
        this.borrowId = borrowId;
        this.memberId = memberId;
    }
}
