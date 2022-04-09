package app.hdj.datepick.domain.user.service;

import app.hdj.datepick.domain.user.dto.UserModifyRequest;
import app.hdj.datepick.domain.user.dto.UserPublic;
import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.repository.UserRepository;
import app.hdj.datepick.global.common.ImageUrl;
import app.hdj.datepick.global.config.file.FileService;
import app.hdj.datepick.global.enums.Gender;
import app.hdj.datepick.global.error.enums.ErrorCode;
import app.hdj.datepick.global.error.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FileService fileService;

    @PersistenceContext
    private EntityManager em;

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

        fileService.remove(imageUrl);
    }

}
