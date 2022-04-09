package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.FirebaseUserInfo;
import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.global.config.security.exception.CustomFirebaseAuthException;
import app.hdj.datepick.global.enums.Provider;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import com.google.firebase.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoogleOAuthRequester implements OAuthRequester {

    private final FirebaseAuth firebaseAuth;

    @Override
    public OAuthUserInfo getUserInfo(String token) {
        try {
            FirebaseToken firebaseToken = firebaseAuth.verifyIdToken(token);
            return FirebaseUserInfo.builder()
                    .uid(firebaseToken.getUid())
                    .name(firebaseToken.getName())
                    .email(firebaseToken.getEmail())
                    .provider(Provider.GOOGLE)
                    .build();
        } catch (FirebaseAuthException e) {
            throw new CustomFirebaseAuthException(e.getAuthErrorCode());
        }
    }

    @Override
    public boolean supports(Provider provider) {
        return provider.equals(Provider.GOOGLE);
    }

}
