package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.RefreshToken;
import app.hdj.datepick.domain.auth.repository.RefreshTokenRepository;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import app.hdj.datepick.global.util.HashingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void saveToken(String token, User user, String uuid, LocalDateTime expireAt) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndUserId(token, user.getId())
                .orElseGet(() -> RefreshToken.builder().build());
        refreshToken.setToken(HashingUtil.hash(token));
        refreshToken.setUserId(user.getId());
        refreshToken.setUuid(uuid);
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
    public void deactivateToken(Long userId, String uuid) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserIdAndUuid(userId, uuid) // TODO: 토큰 해싱해서 넘기기
                .orElseThrow(() -> new CustomException(ErrorCode.TOKEN_INVALID));
        refreshToken.setExpireAt(LocalDateTime.now());
        refreshTokenRepository.save(refreshToken);
    }

}
