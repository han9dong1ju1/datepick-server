package app.hdj.datepick.global.error.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "올바르지 않은 입력입니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),
    TOKEN_EXPIRED(401, "토큰이 만료되었습니다."),
    TOKEN_INVALID(401, "토큰이 유효하지 않습니다."),
    ACCESS_DENIED(403, "접근 권한이 없습니다."),
    ENTITY_NOT_FOUND(404, "요청한 자원을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 생겼습니다."),
    NOT_IMPLEMENTED(501, "아직 구현되지 않은 기능입니다."),

    // AWS
    FILE_NOT_EXISTS(400, "첨부된 파일이 없습니다."),
    FILE_ALREADY_EXISTS(409, "파일이 이미 존재합니다."),
    FILE_UPLOAD_FAILED(500, "파일 업로드에 실패하였습니다."),

    // User
    USER_ALREADY_EXISTS(409, "유저가 이미 존재합니다."),
    USER_REGISTER_FAILED(500, "유저 가입에 실패했습니다."),
    USER_UNREGISTER_FAILED(500, "유저 탈퇴에 실패했습니다."),
    USER_UNREGISTERED(404, "탈퇴한 유저입니다."),

    // Pick
    PICK_ALREADY_EXISTS(409, "이미 픽했습니다."),
    PICK_NOT_EXISTS(409, "아직 픽하지 않았습니다.")
    ;

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
