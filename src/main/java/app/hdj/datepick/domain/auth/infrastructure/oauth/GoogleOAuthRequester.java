package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.global.enums.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GoogleOAuthRequester implements OAuthRequester {

    @Override
    public OAuthUserInfo getUserInfo(String token) {
        return null;
    }

    @Override
    public boolean supports(Provider provider) {
        return provider.equals(Provider.GOOGLE);
    }

}
