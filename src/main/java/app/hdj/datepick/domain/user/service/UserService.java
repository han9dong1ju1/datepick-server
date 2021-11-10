package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.UserMetaDto;
import app.hdj.datepick.domain.user.dto.UserModifyDto;
import app.hdj.datepick.domain.user.dto.UserUnregisterDto;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.config.s3.AmazonS3Service;
import app.hdj.datepick.global.config.security.model.TokenUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final AmazonS3Service amazonS3Service;

    public User getUser(TokenUser tokenUser) {
        return userRepository.findById(tokenUser.getId()).orElseThrow();
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
    @PreAuthorize("#tokenUser.id == #userId")
    public User modifyUser(TokenUser tokenUser, Long userId, UserModifyDto userModifyDto, Boolean removePhoto) {
        User user = userRepository.findById(userId).orElseThrow();

        if (removePhoto) {
            amazonS3Service.remove(user.getProfileUrl());
            user.setProfileUrl(null);
        }

        user.setGender(userModifyDto.getGender());
        user.setNickname(userModifyDto.getNickname());
        return userRepository.save(user);
    }

    @Transactional
    public void unregisterUser(TokenUser tokenUser, UserUnregisterDto userUnregisterRequestDto) {
        // TODO: 삭제 사유 DB에 저장
        log.debug("User Delete 삭제 사유: {}", userUnregisterRequestDto.getReason());

        // User 삭제
        userRepository.deleteById(tokenUser.getId());

        // Firebase User 삭제
        try {
            FirebaseAuth.getInstance().deleteUser(tokenUser.getUid());
            log.debug("Firebase User 삭제 완료");
        } catch (FirebaseAuthException e) {
            log.error("User Unregister 오류: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());     // TODO: user custom exception 만들기
        }
    }

    @Transactional
    public void registerUser(String provider, String token, MultipartFile image) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Optional<FirebaseToken> firebaseTokenOptional;

        // Firebase Token (google)
        if (Objects.equals(provider, "firebase")) {
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

        // 프로필 사진 설정
        String imageKey = null;
        try {
            imageKey = amazonS3Service.add(image, String.format("profile-image/%s", uid));
        } catch (IOException e) {
            throw new RuntimeException("S3 프로필 이미지 없로드 실패");
        }

        // DB User 생성
        User user = User.builder()
                .uid(uid)
                .nickname(name)
                .profileUrl(imageKey)
                .build();
        user = userRepository.save(user);

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

    }
}
