package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Provider;
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
