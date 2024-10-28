package refactoring.bookvillage.domain.borrow.controller.commentdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BorrowCommentResponse {

    private Long id;
    private Long borrowId;
    private String comment;
    private Long memberId;
    private String writer;

}
