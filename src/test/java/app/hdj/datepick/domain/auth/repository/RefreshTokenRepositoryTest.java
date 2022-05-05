package app.hdj.datepick.domain.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowableOfType;

import app.hdj.datepick.RepositoryTest;
import app.hdj.datepick.domain.auth.entity.RefreshToken;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

class RefreshTokenRepositoryTest extends RepositoryTest {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken savedToken;
    private RefreshToken newToken;

    @BeforeEach
    void setUp() {
        savedToken = RefreshToken.builder().token("token1").userId(1L).uuid("uuid1")
            .expireAt(LocalDateTime.now()).build();
        refreshTokenRepository.save(savedToken);
        newToken = RefreshToken.builder().token("token2").userId(2L).uuid("uuid2")
            .expireAt(LocalDateTime.now()).build();
    }

    @Test
    @DisplayName("저장된 토큰이 없는 유저는 새로운 토큰을 저장할 수 있다.")
    void saveNewTokenWithNewUser() {
        // given, when
        newToken = refreshTokenRepository.save(newToken);

        // then
        assertThat(newToken.getId()).isNotNull();
        assertThat(newToken).isEqualTo(newToken);
    }

    @Test
    @DisplayName("저장된 토큰이 있는 유저도 새로운 토큰을 저장할 수 있다.")
    void saveNewTokenWithSameUser() {
        // given
        newToken.setUserId(savedToken.getUserId());

        // when
        newToken = refreshTokenRepository.save(newToken);

        // then
        assertThat(newToken.getId()).isNotNull();
        assertThat(newToken).isEqualTo(newToken);
    }

    @Test
    @DisplayName("이미 존재하는 토큰을 저장하면 예외를 발생시킨다.")
    void saveSameToken() {
        // given
        newToken.setToken(savedToken.getToken());

        // when
        Exception throwable = catchThrowableOfType(() -> refreshTokenRepository.save(newToken),
                                                   Exception.class);

        // then
        assertThat(throwable).isInstanceOf(DataAccessException.class);
    }

    @Test
    @DisplayName("이미 존재하는 uuid값의 토큰을 저장하면 예외를 발생시킨다.")
    void saveSameUuid() {
        // given
        newToken.setUuid(savedToken.getUuid());

        // when
        Exception throwable = catchThrowableOfType(() -> refreshTokenRepository.save(newToken),
                                                   Exception.class);

        // then
        assertThat(throwable).isInstanceOf(DataAccessException.class);
    }

    @Test
    @DisplayName("저장된 uuid로 유효한 토큰을 찾을 수 있다.")
    void findValidTokenBySavedUuid() {
        // given
        LocalDateTime now = savedToken.getExpireAt().minusHours(1);
        String savedTokenUuid = savedToken.getUuid();

        // when
        RefreshToken foundRefreshToken = refreshTokenRepository.findByUuidAndExpireAtAfter(
            savedTokenUuid, now).orElseThrow();

        // then
        assertThat(foundRefreshToken).isEqualTo(savedToken);
    }

    @Test
    @DisplayName("저장된 uuid로 만료된 토큰을 찾으면 예외를 발생시킨다.")
    void findExpiredTokenBySavedUuid() {
        // given
        LocalDateTime now = savedToken.getExpireAt().plusHours(1);
        String savedTokenUuid = savedToken.getUuid();

        // when
        Exception throwable = catchThrowableOfType(
            () -> refreshTokenRepository.findByUuidAndExpireAtAfter(savedTokenUuid, now)
                .orElseThrow(), Exception.class);

        // then
        assertThat(throwable).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("저장되지 않은 uuid로 토큰을 찾으면 예외를 발생시킨다.")
    void findTokenByUnsavedUuid() {
        // given
        LocalDateTime now = savedToken.getExpireAt().minusHours(1);
        String newTokenUuid = newToken.getUuid();

        // when
        Exception throwable = catchThrowableOfType(
            () -> refreshTokenRepository.findByUuidAndExpireAtAfter(newTokenUuid, now)
                .orElseThrow(), Exception.class);

        // then
        assertThat(throwable).isInstanceOf(NoSuchElementException.class);
    }
}