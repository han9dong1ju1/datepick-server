package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.global.enums.Provider;

public interface OAuthRequester {
    OAuthUserInfo getUserInfo(String token);
    boolean supports(Provider provider);
}