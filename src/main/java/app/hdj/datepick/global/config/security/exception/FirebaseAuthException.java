package app.hdj.datepick.global.config.security.exception;

import app.hdj.datepick.global.error.exception.CustomException;
import com.google.firebase.auth.AuthErrorCode;
import org.springframework.http.HttpStatus;

public class FirebaseAuthException extends CustomException {
    public FirebaseAuthException(AuthErrorCode authErrorCode) {
        super(HttpStatus.UNAUTHORIZED, authErrorCode.toString(), "Firebase Auth 오류");
    }
}
