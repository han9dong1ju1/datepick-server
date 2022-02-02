package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.global.enums.Gender;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@AllArgsConstructor
public class UserPublic {

    private Long id;

    private String nickname;

    private Gender gender;

    private String imageUrl;

}
