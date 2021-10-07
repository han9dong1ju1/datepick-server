package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.UserMetaDto;
import app.hdj.datepick.domain.user.dto.UserModifyDto;
import app.hdj.datepick.domain.user.dto.UserUnregisterDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public UserMetaDto getUserPublic(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserMetaDto(
                user.getId(),
                user.getNickname(),
                user.getGender(),
                user.getProfileUrl()
        );
    }

    @Transactional
    public User modifyUser(Long userId, UserModifyDto userModifyDto) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setGender(userModifyDto.getGender());
        user.setNickname(userModifyDto.getNickname());
        user.setProfileUrl(userModifyDto.getProfileUrl());
        return userRepository.save(user);
    }

    @Transactional
    public void unregisterUser(Long id, String uid, UserUnregisterDto userUnregisterRequestDto) {
        // TODO: 삭제 사유 DB에 저장
        log.debug("User Delete 삭제 사유: {}", userUnregisterRequestDto.getReason());

        // User 삭제
        userRepository.deleteById(id);

        // Firebase User 삭제
        try {
            FirebaseAuth.getInstance().deleteUser(uid);
        } catch (FirebaseAuthException e) {
            log.error("User Unregister 오류: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());     // TODO: user register exception 만들기
        }
    }

    @Transactional
    public void registerUser(String provider, String token) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseToken firebaseToken = null;

        // Firebase Token (google)
        if (Objects.equals(provider, "firebase")) {
            // token 유효성 검사
            try {
                firebaseToken = Optional.of(firebaseAuth.verifyIdToken(token)).orElseThrow(RuntimeException::new);
            } catch (FirebaseAuthException e) {
                log.error("Register 오류: {}",  e.getMessage());
                throw new RuntimeException(e.getMessage(), e.getCause());   // TODO: user register exception 만들기
            }
        }
        // Custom Token
        else {
            // TODO: Custom 토큰으로 firebase 유저 생성
            // firebaseAuth.createCustomToken(token);
            firebaseToken = null;
        }

        // Firebase Token 정보
        String uid = firebaseToken.getUid();
        String name = firebaseToken.getName();
        String picture = firebaseToken.getPicture();

        // TODO: Nickname 없을 시 자동 생성
        if (name == null) {
            // 자동 생성 로직
            name = "자동생성된 이름";
        }
        // Nickname 길이 제한 수정
        else if (name.length() > 16) {
            name = name.substring(0, 15);
        }

        // DB User 생성
        User user = User.builder()
                .uid(uid)
                .nickname(name)
                .profileUrl(picture)
                .build();
        user = userRepository.save(user);

        // Custom Claim에 id 추가
        Map<String, Object> customClaims = Map.of("id", user.getId());
        try {
            firebaseAuth.setCustomUserClaims(uid, customClaims);
        } catch (FirebaseAuthException e) {
            log.error("Register Custom Claim 오류: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        // TODO: Custom Claim에 권한 추가

    }
}
