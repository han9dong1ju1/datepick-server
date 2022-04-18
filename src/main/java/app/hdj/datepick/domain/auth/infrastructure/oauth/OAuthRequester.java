package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Provider;

public interface OAuthRequester {
    boolean supports(Provider provider);
    OAuthUserInfo getUserInfo(String code);
}