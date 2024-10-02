package blending.board.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    UploadLessonSuccess(200, "L001", "레슨 업로드 성공"),
    FindAllLessonSuccess(200, "L002", "레슨 전체 조회 성공"),
    DeleteLessonSuccess(200, "L003", "레슨 삭제 성공"),
    RegisterLessonSuccess(200, "L004", "레슨 등록 성공"),
    UploadPostSuccess(200, "P001", "게시글 등록 성공"),
    CreateProfileSuccess(200, "MP001", "프로필 생성 성공"),
    ChangePasswordSuccess(200, "MP002", "비밀번호 변경 성공"),
    UploadMusicDataSuccess(200, "MD001", "음악 자료 업로드 성공"),
    DeleteMusicDataSuccess(200, "MD001", "음악 자료 삭제 성공"),
    PaymentSuccess(200, "PM001", "결제 성공")
    ;

    private final int status;
    private final String code;
    private final String message;
}