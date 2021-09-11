package app.hdj.datepick.global.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // Featured
    FEATURED_NOT_FOUND(404, "F01", "피처드를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
