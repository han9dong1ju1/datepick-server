package app.hdj.datepick.domain.auth.infrastructure;

import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorizationExtractorTest {

    private String headerValue;
    private MockHttpServletRequest request;

    @BeforeEach
    void setUp() {
        headerValue = "headerValue";
        request = new MockHttpServletRequest();
    }

    @Test
    @DisplayName("Authorization 헤더에 값이 있을 경우에 그 값을 반환한다.")
    void extractExistingAuthorizationHeaderValue() {
        // given
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + headerValue);

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isEqualTo(headerValue);
    }

    @Test
    @DisplayName("Authorization 헤더의 타입이 Bearer가 아닐 경우에 null을 반환한다.")
    void extractWrongTypeAuthorizationHeaderValue() {
        // given
        request.addHeader(HttpHeaders.AUTHORIZATION, "Basic Og= " + headerValue);

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isNull();
    }

    @Test
    @DisplayName("Authorization 헤더가 Bearer로 시작하지만 값이 붙어있을 경우에 null을 반환한다.")
    void extractConcatTypeValueAuthorizationHeaderValue() {
        // given
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer" + headerValue);

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isNull();
    }

    @Test
    @DisplayName("Authorization 헤더에 값이 비어있을 경우에 null을 반환한다.")
    void extractEmptyAuthorizationHeaderValue() {
        // given
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer ");

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isNull();
    }

    @Test
    @DisplayName("Authorization 헤더가 없을 경우에 null을 반환한다.")
    void extractNonexistentAuthorizationHeaderValue() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isNull();
    }

}