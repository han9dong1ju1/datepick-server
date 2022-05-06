package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequest {

    @ValueOfEnum(enumClass = Gender.class)
    private String gender;
    @Length(max = 16)
    private String nickname;
}
