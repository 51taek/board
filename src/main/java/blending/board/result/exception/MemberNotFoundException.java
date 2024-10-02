package blending.board.result.exception;

import blending.board.result.ErrorCode;

public class MemberNotFoundException extends ApiException{

    public MemberNotFoundException() {
        super(ErrorCode.MemberNotFoundException);
    }
}
