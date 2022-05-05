package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GoogleOAuthRequester implements OAuthRequester {

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String tokenRequestUri;
    private final String userRequestUri;

    public GoogleOAuthRequester(
        @Value("${security.oauth.google.client-id}") final String clientId,
        @Value("${security.oauth.google.client-secret}") final String clientSecret,
        @Value("${security.oauth.google.redirect-uri}") final String redirectUri,
        @Value("${security.oauth.google.token-request-uri}") final String tokenRequestUri,
        @Value("${security.oauth.google.user-request-uri}") final String userRequestUri
    ) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.tokenRequestUri = tokenRequestUri;
        this.userRequestUri = userRequestUri;
    }

    @Override
    public boolean supports(Provider provider) {
        return provider.equals(Provider.GOOGLE);
    }

    @Override
    public OAuthUserInfo getUserInfo(String code) {
        String accessToken = getAccessToken(code);
        return getUserInfoByAccessToken(accessToken);
    }

    private String getAccessToken(String code) {
        Map<String, Object> responseBody = WebClient.builder().baseUrl(tokenRequestUri).build()
            .post().uri(
                uriBuilder -> uriBuilder.queryParam("code", code).queryParam("client_id", clientId)
                    .queryParam("client_secret", clientSecret)
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("grant_type", "authorization_code").build())
            .headers(httpHeaders -> {
                httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            }).retrieve().onStatus(status -> !status.is2xxSuccessful(), response -> {
                throw new CustomException(ErrorCode.OAUTH_REQUEST_FAILED);
            }).bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
            }).blockOptional()
            .orElseThrow(() -> new CustomException(ErrorCode.OAUTH_REQUEST_FAILED));
        if (!responseBody.containsKey("access_token")) {
            throw new CustomException(ErrorCode.OAUTH_SERVER_ERROR);
        }
        return String.valueOf(responseBody.get("access_token"));
    }

    private GoogleUserInfo getUserInfoByAccessToken(String accessToken) {
        Map<String, Object> responseBody = WebClient.builder().baseUrl(userRequestUri).build().get()
            .headers(httpHeaders -> {
                httpHeaders.setBearerAuth(accessToken);
                httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                httpHeaders.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));
            }).retrieve().onStatus(status -> !status.is2xxSuccessful(), response -> {
                throw new CustomException(ErrorCode.OAUTH_REQUEST_FAILED);
            }).bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
            }).blockOptional()
            .orElseThrow(() -> new CustomException(ErrorCode.OAUTH_REQUEST_FAILED));
        return GoogleUserInfo.from(responseBody);
    }
}
