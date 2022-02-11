package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.UserModifyRequest;
import app.hdj.datepick.domain.user.dto.UserPublic;
import app.hdj.datepick.domain.user.dto.UserRegisterRequest;
import app.hdj.datepick.domain.user.dto.UserUnregisterRequest;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.entity.UserUnregisterLog;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.domain.user.repository.UserUnregisterLogRepository;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.config.file.FileService;
import app.hdj.datepick.global.enums.Gender;
import app.hdj.datepick.global.enums.Provider;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserUnregisterLogRepository userUnregisterLogRepository;

    private final FileService fileService;

    private final FirebaseAuth firebaseAuth;

    @PersistenceContext
    private final EntityManager em;

    public UserPublic getPublicUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        // 삭제한 유저일 경우
        if (user.getIsDeleted()) {
            throw new CustomException(ErrorCode.USER_UNREGISTERED);
        }

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
    public ImageUrl addUserImage(Long userId, MultipartFile image) {
        User user = userRepository.findById(userId).orElseThrow();
        String imageUrl = user.getImageUrl();

        if (imageUrl != null) {
            throw new CustomException(ErrorCode.FILE_ALREADY_EXISTS);
        }
        imageUrl = fileService.add(image, "profile-image/" + userId);
        user.setImageUrl(imageUrl);
        userRepository.save(user);

        return new ImageUrl(imageUrl);
    }

    @Transactional
    public void removeUserImage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        String imageUrl = user.getImageUrl();
        if (imageUrl == null) {
            throw new NoSuchElementException();
        }

        user.setImageUrl(null);
        userRepository.save(user);

        fileService.remove(imageUrl);
    }

    @Transactional
    public User registerUser(UserRegisterRequest userRegisterRequest) {
        FirebaseToken firebaseToken = null;

        Provider provider = Provider.from(userRegisterRequest.getProvider());
        String token = userRegisterRequest.getToken();

        if (provider == Provider.GOOGLE) {
            // token 유효성 검사
            try {
                firebaseToken = firebaseAuth.verifyIdToken(token);
            } catch (FirebaseAuthException e) {
                throw new CustomException(ErrorCode.TOKEN_INVALID);
            }
        }
        else if (provider == Provider.KAKAO) {
            // 보류
            throw new CustomException(ErrorCode.NOT_IMPLEMENTED);
        }
        
        // FirebaseToken이 null일 경우
        if (firebaseToken == null) {
            throw new CustomException(ErrorCode.TOKEN_INVALID);
        }

        // Firebase Token 정보
        String uid = firebaseToken.getUid();
        String name = firebaseToken.getName();
        String firebaseImageUrl = firebaseToken.getPicture();

        // 이미 생성된 계정이 있는지 중복 확인
        if (userRepository.existsByUid(uid)) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        // name 없을 시 자동 생성
        if (name == null) {
            name = "user_" + RandomStringUtils.randomNumeric(11);
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
                .build();
        user = userRepository.save(user);
        // 로컬 캐시 갱신
        em.refresh(user);

        // Custom Claim에 id 및 권한 추가
        Map<String, Object> customClaims = Map.of(
                "id", user.getId(),
                "authorities", Set.of("USER"));
        try {
            firebaseAuth.setCustomUserClaims(uid, customClaims);
        } catch (FirebaseAuthException e) {
            log.error("Register Custom Claim 오류: {}", e.getMessage());
            throw new CustomException(ErrorCode.USER_REGISTER_FAILED);
        }

        return user;
    }

    @Transactional
    public void unregisterUser(Long userId, UserUnregisterRequest userUnregisterRequest) {
        User user = userRepository.findById(userId).orElseThrow();

        // 삭제 사유 DB에 저장
        UserUnregisterLog userUnregisterLog = UserUnregisterLog.builder()
                .user(user)
                .reason(userUnregisterRequest.getReason())
                .content(userUnregisterRequest.getContent())
                .expireAt(LocalDateTime.now().plusYears(1))
                .build();
        userUnregisterLogRepository.save(userUnregisterLog);

        // User 삭제 된 것으로 처리
        user.setIsDeleted(true);
        userRepository.save(user);

        // Firebase User 정지 (완전 삭제 처리는 수동 or batch 후처리)
        try {
            firebaseAuth.updateUser(
                    new UserRecord.UpdateRequest(user.getUid())
                            .setDisabled(true)
            );
            log.debug("Firebase User 정지 처리 완료");
        } catch (FirebaseAuthException e) {
            log.debug(e.getMessage());
            throw new CustomException(ErrorCode.USER_UNREGISTER_FAILED);
        }

    }

}
