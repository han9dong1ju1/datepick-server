package app.hdj.datepick.global.common;

import app.hdj.datepick.global.annotation.ImageFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageUploadRequest {

    @NotNull
    @ImageFile
    private MultipartFile image;

}
