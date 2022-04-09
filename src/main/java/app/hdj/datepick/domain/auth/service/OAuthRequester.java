package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.global.enums.Provider;

public interface OAuthRequester {
    OAuthUserInfo getUserInfo(String token);
    boolean supports(Provider provider);
}