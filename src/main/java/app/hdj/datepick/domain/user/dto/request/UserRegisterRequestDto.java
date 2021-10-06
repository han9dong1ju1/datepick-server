package app.hdj.datepick.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequestDto {

    @NotNull
    private String provider;

    @NotNull
    private String token;

}
