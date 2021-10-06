package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.request.UserUnregisterRequestDto;
import app.hdj.datepick.domain.user.dto.request.UserUpdateRequestDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Transactional
    public User updateUser(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setGender(userUpdateRequestDto.getGender());
        user.setNickname(userUpdateRequestDto.getNickname());
        user.setProfileUrl(userUpdateRequestDto.getProfileUrl());
        return userRepository.save(user);
    }

    @Transactional
    public void unregisterUser(Long id, String uid, UserUnregisterRequestDto userUnregisterRequestDto) {
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
                firebaseToken = firebaseAuth.verifyIdToken(token);
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

        // Token null 확인
        if (firebaseToken == null) {
            log.error("Register 오류: FirebaseToken is null");
            throw new RuntimeException();   // TODO: user register exception 만들기
        }

        // DB User 생성
        String uid = firebaseToken.getUid();
        String name = firebaseToken.getName();
        String picture = firebaseToken.getPicture();
        User user = new User(uid, "gimotti", 0, "google.com/sex");   // TODO: 수정
        user = userRepository.save(user);

        // Custom Claim에 id 추가
        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("id", user.getId());
        try {
            firebaseAuth.setCustomUserClaims(uid, customClaims);
        } catch (FirebaseAuthException e) {
            log.error("Register Custom Claim 오류: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        // TODO: Custom Claim에 권한 추가

    }
}
