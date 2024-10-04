package refactoring.bookvillage.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BindErrorResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;
    private T data;

    public BindErrorResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> BindErrorResponse<T> of(HttpStatus status, String message, T data) {
        return new BindErrorResponse<>(status, message, data);
    }

    public static <T> BindErrorResponse<T> of(HttpStatus status, T data) {
        return of(status, status.name(), data);
    }

    public static <T> BindErrorResponse<T> of(T data) {
        return of(HttpStatus.OK, HttpStatus.OK.name(), data);
    }

}
