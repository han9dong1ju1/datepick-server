package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.UserRequest;
import app.hdj.datepick.domain.user.dto.UserResponse;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.common.ImageUrlResponse;
import app.hdj.datepick.global.config.file.FileService;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import java.util.NoSuchElementException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileService fileService;

    @PersistenceContext
    private EntityManager em;

    public UserResponse getPublicUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();

        // 삭제한 유저일 경우
        if (user.getIsDeleted()) {
            throw new CustomException(ErrorCode.USER_UNREGISTERED);
        }

        return UserResponse.from(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User modifyUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).orElseThrow();

        Gender gender = Gender.from(userRequest.getGender());
        if (gender != null) {
            user.setGender(gender);
        }

        String nickname = userRequest.getNickname();
        if (nickname != null) {
            user.setNickname(nickname);
        }

        return userRepository.save(user);
    }

    @Transactional
    public ImageUrlResponse addUserImage(Long userId, MultipartFile image) {
        User user = userRepository.findById(userId).orElseThrow();
        String imageUrl = user.getImageUrl();
        if (imageUrl != null) {
            throw new CustomException(ErrorCode.FILE_ALREADY_EXISTS);
        }

        imageUrl = fileService.add(image, "profile-image/" + userId);
        user.setImageUrl(imageUrl);

        return new ImageUrlResponse(imageUrl);
    }

    @Transactional
    public void removeUserImage(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        String imageUrl = user.getImageUrl();
        if (imageUrl == null) {
            throw new NoSuchElementException();
        }

        user.setImageUrl(null);

        fileService.remove(imageUrl);
    }
}
