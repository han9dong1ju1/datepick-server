package app.hdj.datepick.domain.auth.infrastructure.oauth;

import app.hdj.datepick.domain.user.enums.Provider;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;

class GoogleOAuthRequesterTest {

    private static final String GOOGLE_TOKEN_RESPONSE =
            "{\n" +
            "    \"access_token\": \"1/fFAGRNJru1FTz70BzhT3Zg\",\n" +
            "    \"expires_in\": 3920,\n" +
            "    \"refresh_token\": \"1//xEoDL4iW3cxlI7yDbSRFYNG01kVKM2C-259HOF2aQbI\",\n" +
            "    \"scope\": \"https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email openid\",\n" +
            "    \"token_type\": \"Bearer\"\n" +
            "}";
    private static final String GOOGLE_USER_PROFILE_RESPONSE =
            "{\n" +
            "    \"family_name\": \"Gong\",\n" +
            "    \"name\": \"Meda Gong\",\n" +
            "    \"picture\": \"https://lh3.googleusercontent.com/a-/fFAGRNJru1FTz70BzhT3Zg-xEoDL4iW3cxlI7yDbSRFYNG01kVKM2C=s69-d\"," +
            "    \"locale\": \"ko\",\n" +
            "    \"email\": \"gongmeda@gmail.com\",\n" +
            "    \"given_name\": \"Meda\",\n" +
            "    \"id\": \"903471874241403004856\",\n" +
            "    \"verified_email\": true\n" +
            "}";
    private static String serverUri;
    private static MockWebServer mockWebServer;

    @BeforeAll
    static void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        serverUri = String.format("http://%s:%s", mockWebServer.getHostName(), mockWebServer.getPort());
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    @DisplayName("요청의 제공자가 Google이면 참을 반환한다.")
    void supports() {
        // given
        Provider requestedProvider = Provider.GOOGLE;
        GoogleOAuthRequester googleOAuthRequester = new GoogleOAuthRequester(
                "clientId",
                "clientSecret",
                "redirectUri",
                serverUri,
                serverUri
        );

        // when
        boolean result = googleOAuthRequester.supports(requestedProvider);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("유효한 인가 코드로 요청을 보내면 유저 정보를 반환한다.")
    void getUserInfoWithValidCode() {
        // given
        setServerWithValidCodeAndResponse();
        GoogleOAuthRequester googleOAuthRequester = createRequester();

        // when
        OAuthUserInfo userInfo = googleOAuthRequester.getUserInfo("valid code");

        // then
        assertThat(userInfo.getEmail()).isNotNull();
        assertThat(userInfo.getName()).isNotNull();
        assertThat(userInfo.getUid()).isNotNull();
        assertThat(userInfo.getProvider()).isEqualTo(Provider.GOOGLE);
        assertThat(userInfo.getGender()).isNull();
    }

    @Test
    @DisplayName("유효하지 않은 인가 코드로 요청을 보내면 예외를 발생시킨다.")
    void getUserInfoWithInvalidCode() {
        // given
        setServerWithInvalidCodeResponse();
        GoogleOAuthRequester googleOAuthRequester = createRequester();

        // when
        CustomException throwable = catchThrowableOfType(() -> googleOAuthRequester.getUserInfo("invalid code"), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.OAUTH_REQUEST_FAILED);
    }

    @Test
    @DisplayName("구글 인증 서버에서 응답을 못 받으면 예외를 발생시킨다.")
    void getUserInfoWithTokenServerError() {
        // given
        setServerWithTokenServerErrorResponse();
        GoogleOAuthRequester googleOAuthRequester = createRequester();

        // when
        CustomException throwable = catchThrowableOfType(() -> googleOAuthRequester.getUserInfo("invalid code"), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.OAUTH_REQUEST_FAILED);
    }

    @Test
    @DisplayName("구글 API 서버에서 응답을 못 받으면 예외를 발생시킨다.")
    void getUserInfoWithUserServer() {
        // given
        setServerWithUserServerErrorResponse();
        GoogleOAuthRequester googleOAuthRequester = createRequester();

        // when
        CustomException throwable = catchThrowableOfType(() -> googleOAuthRequester.getUserInfo("invalid code"), CustomException.class);

        // then
        assertThat(throwable.getErrorCode()).isEqualTo(ErrorCode.OAUTH_REQUEST_FAILED);
    }

    private void setServerWithValidCodeAndResponse() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(GOOGLE_TOKEN_RESPONSE)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
        mockWebServer.enqueue(new MockResponse()
                .setBody(GOOGLE_USER_PROFILE_RESPONSE)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
    }

    private void setServerWithInvalidCodeResponse() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(400)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
    }

    private void setServerWithTokenServerErrorResponse() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
    }

    private void setServerWithUserServerErrorResponse() {
        mockWebServer.enqueue(new MockResponse()
                .setBody(GOOGLE_TOKEN_RESPONSE)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(500)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
    }

    private GoogleOAuthRequester createRequester() {
        return new GoogleOAuthRequester(
                "clientId",
                "clientSecret",
                "redirectUri",
                serverUri,
                serverUri
        );
    }

}