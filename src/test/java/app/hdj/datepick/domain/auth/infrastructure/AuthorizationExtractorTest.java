package app.hdj.datepick.domain.auth.infrastructure;

import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

class AuthorizationExtractorTest {

    @Test
    @DisplayName("Authorization 헤더에 값이 있을 경우에 그 값을 반환한다.")
    void extractExistingAuthorizationHeaderValue() {
        // given
        String value = "value";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + value);

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isEqualTo(value);
    }

    @Test
    @DisplayName("Authorization 헤더의 타입이 Bearer가 아닐 경우에 null을 반환한다.")
    void extractWrongTypeAuthorizationHeaderValue() {
        // given
        String value = "value";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Basic Og= " + value);

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isNull();
    }

    @Test
    @DisplayName("Authorization 헤더가 Bearer로 시작하지만 값이 붙어있을 경우에 null을 반환한다.")
    void extractConcatTypeValueAuthorizationHeaderValue() {
        // given
        String value = "value";
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader(HttpHeaders.AUTHORIZATION, "Bearer" + value);

        // when
        String extractedToken = AuthorizationExtractor.extract(request);

        // then
        assertThat(extractedToken).isNull();
    }

    @Test
    @DisplayName("Authorization 헤더에 값이 비어있을 경우에 null을 반환한다.")
    void extractEmptyAuthorizationHeaderValue() {
        // given
        MockHttpServletRequest request = new MockHttpServletRequest();
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