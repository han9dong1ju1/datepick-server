package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.global.enums.Provider;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class OAuthHandler {

    private final List<OAuthRequester> oAuthRequesters;

    public OAuthUserInfo getUserInfo(final Provider provider, final String token) {
        OAuthRequester requester = getRequester(provider);
        return requester.getUserInfo(token);
    }

    private OAuthRequester getRequester(final Provider provider) {
        return oAuthRequesters.stream()
                .filter(requester -> requester.supports(provider))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INPUT_VALUE));
    }

}
