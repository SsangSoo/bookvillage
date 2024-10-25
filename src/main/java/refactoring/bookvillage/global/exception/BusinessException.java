package refactoring.bookvillage.global.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.message);
        this.code = exceptionCode.code;

    }

    public enum ExceptionCode {
        NO_CONTENT(204, "해당 게시글을 찾아볼 수 없습니다."),

        INVALID_EXCEPTION(404, "유효하지 않은 접근입니다."),

        NOT_EXIST_CONTENT(404, "해당 글이 존재하지 않습니다."),
        NOT_EXIST_COMMENT(404, "해당 댓글이 존재하지 않습니다."),
        NOT_EXIST_MEMBER(404, "존재하지 않는 회원입니다."),

        DELETED_CONTENT(404, "삭제된 게시글입니다."),
        DELETED_COMMENT(404, "삭제된 댓글입니다."),
        DELETED_MEMBER(404, "삭제된 회원입니다."),

        ALREADY_DELETED_COMMENT(404, "이미 삭제된 댓글입니다."),

        ACCESS_OTHER_WRITER(404, "작성자 외 회원이 접근 중입니다"),

        OTHER_MAIN_CONTENT(404, "작성하고자 하는 글과 다른 글에 접근중입니다.");






        ExceptionCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        int code;
        String message;

    }
}
