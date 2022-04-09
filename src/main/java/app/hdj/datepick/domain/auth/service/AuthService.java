package app.hdj.datepick.domain.auth.service;

import app.hdj.datepick.domain.auth.dto.AuthTokenResponse;
import app.hdj.datepick.domain.auth.dto.OAuthUserInfo;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.domain.user.repository.UserUnregisterLogRepository;
import app.hdj.datepick.global.config.security.util.JwtUtil;
import app.hdj.datepick.global.enums.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserUnregisterLogRepository userUnregisterLogRepository;
    private final UserRepository userRepository;
    private final OAuthHandler oAuthHandler;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthTokenResponse login(final Provider provider, final String token) {
        // OAuth 유저 ID 정보 추출
        OAuthUserInfo userInfo = oAuthHandler.getUserInfo(provider, token);

        // DB 조회 후 유저가 없을 경우 생성
        Optional<User> user = userRepository.findByUid(userInfo.getUid());
        if (user.isEmpty()) {
            user = Optional.of(createUser(userInfo));
        }

        String customToken = jwtUtil.createToken(user.get());
        return AuthTokenResponse.from(customToken);
    }

    private User createUser(OAuthUserInfo userInfo) {
        User user = User.builder()
                .uid(userInfo.getUid())
                .provider(userInfo.getProvider())
                .email(userInfo.getEmail())
                .nickname(createNickname(userInfo.getName()))
                .gender(userInfo.getGender())
                .build();

        return userRepository.save(user);
    }

    private String createNickname(String name) {
        String prefix = Objects.requireNonNullElse(name, "user");
        name = prefix + (int) (Math.random() * Math.pow(10, 11));

        if (name.length() > 16) {
            name = name.substring(0, 15);
        }

        return name;
    }

//    @Transactional
//    public void unregisterUser(Long userId, AuthUnregisterRequest userUnregisterRequest) {
//        User user = userRepository.findById(userId).orElseThrow();
//
//        // 삭제 사유 DB에 저장
//        UserUnregisterLog userUnregisterLog = UserUnregisterLog.builder()
//                .user(user)
//                .reason(userUnregisterRequest.getReason())
//                .content(userUnregisterRequest.getContent())
//                .expireAt(LocalDateTime.now().plusYears(1))
//                .build();
//        userUnregisterLogRepository.save(userUnregisterLog);
//
//        // User 삭제 된 것으로 처리
//        user.setIsDeleted(true);
//        userRepository.save(user);
//
//        // Firebase User 정지 (완전 삭제 처리는 수동 or batch 후처리)
//        try {
//            firebaseAuth.updateUser(
//                    new UserRecord.UpdateRequest(user.getUid())
//                            .setDisabled(true)
//            );
//            log.debug("Firebase User 정지 처리 완료");
//        } catch (FirebaseAuthException e) {
//            log.debug(e.getMessage());
//            throw new CustomException(ErrorCode.USER_UNREGISTER_FAILED);
//        }
//
//    }

}
