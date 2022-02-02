package app.hdj.datepick.global.config.security.exception;

import app.hdj.datepick.global.error.exception.CustomException;
import org.springframework.http.HttpStatus;

public class TokenNoClaimException extends CustomException {
    public TokenNoClaimException(String field) {
        super(HttpStatus.UNAUTHORIZED, "TOKEN_NO_CLAIM", "토큰에 " + field + " 정보가 없습니다.");
    }
}
