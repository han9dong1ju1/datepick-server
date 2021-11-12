package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.global.common.enums.Gender;
import app.hdj.datepick.global.common.validator.ImageFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModifyDto {

    @NotNull
    private Gender gender;

    @NotEmpty
    @Length(max = 16)
    private String nickname;

    @ImageFile
    private MultipartFile image;

}
