package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.UserModifyRequest;
import app.hdj.datepick.domain.user.dto.UserPublic;
import app.hdj.datepick.domain.user.dto.UserRegisterRequest;
import app.hdj.datepick.domain.user.dto.UserUnregisterDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.config.s3.AmazonS3Service;
import app.hdj.datepick.global.enums.Gender;
import app.hdj.datepick.global.enums.Provider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final AmazonS3Service amazonS3Service;

    @PersistenceContext
    private final EntityManager em;

    public UserPublic getPublicUser(Long id) {
        User user = userRepository.findUserByIsDeletedFalseAndIsBannedFalseAndId(id).orElseThrow();
        return new UserPublic(
                user.getId(),
                user.getNickname(),
                user.getGender(),
                user.getImageUrl()
        );
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User modifyUser(Long userId, UserModifyRequest userModifyRequest) {
        User user = userRepository.findById(userId).orElseThrow();

        Gender gender = Gender.from(userModifyRequest.getGender());
        if (gender != null) {
            user.setGender(gender);
        }

        String nickname = userModifyRequest.getNickname();
        if (nickname != null) {
            user.setNickname(nickname);
        }

        return userRepository.save(user);
    }

    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Optional<FirebaseToken> firebaseTokenOptional;

        Provider provider = Provider.from(userRegisterRequest.getProvider());
        String token = userRegisterRequest.getToken();

        // Google
        if (provider == Provider.GOOGLE) {
            // token 유효성 검사
            try {
                firebaseTokenOptional = Optional.of(firebaseAuth.verifyIdToken(token));
            } catch (FirebaseAuthException e) {
                log.error("Register 오류: {}",  e.getMessage());
                throw new RuntimeException(e.getMessage(), e.getCause());   // TODO: user custom exception 만들기
            }
        }
        // Custom Token
        else {
            // TODO: Custom 토큰으로 firebase 유저 생성
            // firebaseAuth.createCustomToken(token);
            firebaseTokenOptional = Optional.empty();
        }

        // Firebase Token 정보
        FirebaseToken firebaseToken = firebaseTokenOptional.orElseThrow();      // TODO: user custom exception 만들기
        String uid = firebaseToken.getUid();
        String name = firebaseToken.getName();
        String picture = firebaseToken.getPicture();

        // 이미 생성된 계정이 있는지 중복 확인
        if (userRepository.existsByUid(uid)) {
            throw new RuntimeException("이미 생성된 계정 있음");     // TODO: user custom exception 만들기
        }

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
                .provider(provider)
                .nickname(name)
                .imageUrl(picture)
                .build();
        user = userRepository.save(user);
        em.refresh(user);

        // Custom Claim에 id 및 권한 추가
        Map<String, Object> customClaims = Map.of(
                "id", user.getId(),
                "authorities", Set.of("USER"));
        try {
            firebaseAuth.setCustomUserClaims(uid, customClaims);
        } catch (FirebaseAuthException e) {
            log.error("Register Custom Claim 오류: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        return user;
    }

    @Transactional
    public void unregisterUser(Long userId, UserUnregisterDto userUnregisterRequestDto) {
        User user = userRepository.findById(userId).orElseThrow();

        // TODO: 삭제 사유 DB에 저장
        log.debug("User Delete 삭제 사유: {}", userUnregisterRequestDto.getReason());

        // User 삭제
        userRepository.deleteById(user.getId());

        // Firebase User 삭제
        try {
            FirebaseAuth.getInstance().deleteUser(user.getUid());
            log.debug("Firebase User 삭제 완료");
        } catch (FirebaseAuthException e) {
            log.error("User Unregister 오류: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());     // TODO: user custom exception 만들기
        }

        // S3 프로필 사진 삭제
        String profileUrl = user.getImageUrl();
        if (profileUrl != null) {
            amazonS3Service.remove(profileUrl);
        }
    }

}
