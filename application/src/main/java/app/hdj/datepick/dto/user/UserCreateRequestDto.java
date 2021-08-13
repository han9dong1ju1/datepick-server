package app.hdj.datepick.dto.user;

import app.hdj.datepick.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    private Character gender;
    private String nickname;
    private String profileUrl;

    public User createUser() {
        return new User(
                null,
                null,
                this.gender,
                this.nickname,
                this.profileUrl
        );
    }
}
