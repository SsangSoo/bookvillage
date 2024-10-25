package refactoring.bookvillage.domain.borrowcomment.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BorrowCommentResponse {

    private Long id;
    private Long borrowId;
    private String comment;
    private Long memberId;
    private String writer;

}
