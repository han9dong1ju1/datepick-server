package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.global.annotation.ValueOfEnum;
import app.hdj.datepick.global.enums.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

    @NotNull
    @ValueOfEnum(enumClass = Provider.class)
    private String provider;

    @NotNull
    private String token;

}
