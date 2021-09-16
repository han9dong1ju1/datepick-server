package app.hdj.datepick.domain.user.dto;

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

}
