package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.domain.user.enums.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {

    private Long id;
    private String nickname;
    private Gender gender;
    private String imageUrl;

    public static UserResponse from(User user) {
        return new UserResponse(user.getId(),
                                user.getNickname(),
                                user.getGender(),
                                user.getImageUrl());
    }
}
