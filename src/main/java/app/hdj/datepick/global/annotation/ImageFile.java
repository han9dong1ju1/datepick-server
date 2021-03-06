package app.hdj.datepick.global.annotation;

import app.hdj.datepick.global.annotation.validator.ImageFileValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImageFileValidator.class)
public @interface ImageFile {

    String message() default "유효하지 않은 이미지 파일 형식입니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}