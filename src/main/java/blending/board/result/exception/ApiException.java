package blending.board.result.exception;

import blending.board.result.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{

    private ErrorCode errorCode;

    public ApiException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
