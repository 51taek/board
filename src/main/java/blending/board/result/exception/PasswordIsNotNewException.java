package blending.board.result.exception;

import blending.board.result.ErrorCode;

public class PasswordIsNotNewException extends ApiException{

    public PasswordIsNotNewException() {
        super(ErrorCode.PasswordIsNotNewException);
    }

}