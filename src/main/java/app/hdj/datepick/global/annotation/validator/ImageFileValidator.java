package app.hdj.datepick.global.annotation.validator;

import app.hdj.datepick.global.annotation.ImageFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

@Slf4j
public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public void initialize(ImageFile constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile != null) {
            String contentType = multipartFile.getContentType();
            return isSupportedContentType(Objects.requireNonNull(contentType));
        }
        return true;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.startsWith("image/");
    }
}