package blending.board.result.exception;

import blending.board.result.ErrorCode;

public class LessonNotFoundException extends ApiException{

    public LessonNotFoundException() {
        super(ErrorCode.LessonNotFoundException);
    }
}
