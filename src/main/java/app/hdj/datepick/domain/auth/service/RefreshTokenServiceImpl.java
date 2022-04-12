package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.RefreshToken;
import app.hdj.datepick.domain.auth.repository.RefreshTokenRepository;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void saveToken(String token, Long userId, LocalDateTime expireAt) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndUserId(token, userId)
                .orElseGet(() -> RefreshToken.builder().build());
        refreshToken.setToken(token); // TODO: 토큰 해싱해서 저장하기
        refreshToken.setUserId(userId);
        refreshToken.setExpireAt(expireAt);
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Long getUserIdByToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndExpireAtAfter(token, LocalDateTime.now())
                .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_INVALID));
        return refreshToken.getUserId();
    }

    @Override
    public void deactivateToken(String token, Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndUserId(token, userId)
                .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_INVALID));
        refreshToken.setExpireAt(LocalDateTime.now());
        refreshTokenRepository.save(refreshToken);
    }

}
