package blending.board.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MemberNotFoundException(400, "M501", "멤버를 찾을 수 없습니다."),
    PasswordNotMatchException(400, "M502", "패스워드가 일치하지 않습니다."),
    PasswordIsNotNewException(400, "M503", "새 패스워드가 이전 패스워드와 일치합니다"),
    MusicDataNotFoundException(400, "MD501", "음악자료를 찾을 수 없습니다"),
    LessonNotFoundException(400, "L501", "레슨을 찾을 수 없습니다"),
    MemberAlreadyExistException(400, "M003", "회원이 이미 존재합니다"),
    NotWriterException(400, "W001", "작성자가 일치하지 않습니다.")
    ;

    private final int status;
    private final String code;
    private final String message;
}
