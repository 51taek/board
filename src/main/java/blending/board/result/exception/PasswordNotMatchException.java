package blending.board.result.exception;

import blending.board.result.ErrorCode;

public class PasswordNotMatchException extends ApiException{

    public PasswordNotMatchException() {
        super(ErrorCode.PasswordNotMatchException);
    }

}
