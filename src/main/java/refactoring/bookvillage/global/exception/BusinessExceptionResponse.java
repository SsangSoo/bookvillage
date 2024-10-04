package refactoring.bookvillage.global.exception;

import lombok.Getter;

@Getter
public class BusinessExceptionResponse {

    private int code;
    private String message;

    public BusinessExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BusinessExceptionResponse of(int code, String message) {
        return new BusinessExceptionResponse(code, message);
    }

    public static BusinessExceptionResponse of(BusinessException e) {
        return new BusinessExceptionResponse(e.getCode(), e.getMessage());
    }
}
