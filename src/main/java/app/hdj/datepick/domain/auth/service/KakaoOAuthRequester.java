package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.global.enums.Provider;
import org.springframework.stereotype.Component;

@Component
public class KakaoOAuthRequester implements OAuthRequester {

    @Override
    public OAuthUserInfo getUserInfo(String token) {
        // TODO
        return null;
    }

    @Override
    public boolean supports(Provider provider) {
        return provider.equals(Provider.KAKAO);
    }

}
