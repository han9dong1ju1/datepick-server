package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.domain.user.enums.Gender;
import app.hdj.datepick.global.annotation.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyRequest {

    @ValueOfEnum(enumClass = Gender.class)
    private String gender;
    @Length(max = 16)
    private String nickname;
}
