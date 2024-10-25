package refactoring.bookvillage.global.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageResponse {

    private String message;

    public MessageResponse(MessageCode messageCode) {
        this.message = messageCode.message;
    }

    public enum MessageCode {
        BORROW_UPDATED("대여 게시글이 수정되었습니다."),
        BORROW_DELETED("대여 게시글이 삭제되었습니다."),
        BORROW_COMMENT_UPDATED("대여 게시글 댓글이 수정되었습니다."),
        BORROW_COMMENT_DELETED("대여 게시글 댓글이 삭제되었습니다.");

        String message;


        MessageCode(String message) {
            this.message = message;
        }
    }
}