package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Provider;

public interface OAuthRequester {
    OAuthUserInfo getUserInfo(String token);
    boolean supports(Provider provider);
}