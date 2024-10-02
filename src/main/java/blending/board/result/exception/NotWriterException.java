package blending.board.result.exception;

import blending.board.result.ErrorCode;

public class NotWriterException extends ApiException{

    public NotWriterException() {
        super(ErrorCode.NotWriterException);
    }
}
