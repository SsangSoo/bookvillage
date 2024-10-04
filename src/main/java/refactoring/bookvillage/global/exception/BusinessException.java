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
        NO_CONTENT(204, "해당 대여 게시글을 찾아볼 수 없습니다."),
        NO_BORROW(404, "해당 대여 게시글이 존재하지 않습니다."),
        DELETED_CONTENT(404, "잘 못된 접근입니다. 삭제된 대여 게시글입니다."),
        ACCESS_OTHER_WRITER(404, "작성자 외 회원이 접근 중입니다");


        ExceptionCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        int code;
        String message;

    }
}
