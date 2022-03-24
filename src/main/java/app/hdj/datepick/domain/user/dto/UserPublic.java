package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.domain.user.entity.User;
import app.hdj.datepick.global.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPublic {

    private Long id;
    private String nickname;
    private Gender gender;
    private String imageUrl;

    public static UserPublic from(User user) {
        return new UserPublic(
                user.getId(),
                user.getNickname(),
                user.getGender(),
                user.getImageUrl()
        );
    }

}
