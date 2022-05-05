package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OAuthHandler {

    private final List<OAuthRequester> oAuthRequesters;

    public OAuthUserInfo getUserInfo(final Provider provider, final String code) {
        OAuthRequester requester = getRequester(provider);
        return requester.getUserInfo(code);
    }

    private OAuthRequester getRequester(final Provider provider) {
        return oAuthRequesters.stream().filter(requester -> requester.supports(provider))
            .findFirst().orElseThrow(() -> new CustomException(ErrorCode.NOT_IMPLEMENTED));
    }
}
