package app.hdj.datepick.domain.user.dto;

import app.hdj.datepick.global.common.validator.ImageFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotNull
    private String provider;

    @NotNull
    private String token;

    @ImageFile
    private MultipartFile image;

}
