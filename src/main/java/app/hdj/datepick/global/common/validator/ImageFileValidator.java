package app.hdj.datepick.global.common.validator;

import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ImageFileValidator implements ConstraintValidator<ImageFile, MultipartFile> {

    @Override
    public void initialize(ImageFile constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        boolean result = true;

        if (multipartFile != null) {
            String contentType = multipartFile.getContentType();
            if(!isSupportedContentType(Objects.requireNonNull(contentType))) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                                "Only images are allowed.")
                        .addConstraintViolation();

                result = false;
            }
        }

        return result;
    }

    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("image/*");
    }
}