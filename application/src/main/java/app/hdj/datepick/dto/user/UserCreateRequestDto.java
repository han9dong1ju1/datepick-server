package app.hdj.datepick.dto.user;

import app.hdj.datepick.domain.model.User;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    private Character gender;

    @NotNull
    private String nickname;

    @URL
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
