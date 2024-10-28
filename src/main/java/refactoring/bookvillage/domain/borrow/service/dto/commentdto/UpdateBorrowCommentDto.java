package refactoring.bookvillage.domain.borrow.service.dto.commentdto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateBorrowCommentDto {
    private String comment;
    private Long borrowId;
    private Long memberId;
    private Long borrowCommentId;

    @Builder
    private UpdateBorrowCommentDto(String comment, Long borrowId, Long memberId, Long borrowCommentId) {
        this.comment = comment;
        this.borrowId = borrowId;
        this.memberId = memberId;
        this.borrowCommentId = borrowCommentId;
    }
}
