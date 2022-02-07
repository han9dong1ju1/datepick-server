package app.hdj.datepick.global.error.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "올바르지 않은 입력입니다."),
    UNAUTHORIZED(401, "인증되지 않은 사용자입니다."),
    TOKEN_EXPIRED(401, "인증 토큰이 만료되었습니다."),
    TOKEN_INVALID(401, "인증 토큰이 유효하지 않습니다."),
    ACCESS_DENIED(403, "접근 권한이 없습니다."),
    ENTITY_NOT_FOUND(404, "요청한 자원을 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "서버에 오류가 생겼습니다."),
    ;

    //

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
