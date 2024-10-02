package blending.board.result.exception;

import blending.board.result.ErrorCode;

public class MusicDataNotFoundException extends ApiException {

    public MusicDataNotFoundException() {
        super(ErrorCode.MusicDataNotFoundException);
    }
}
