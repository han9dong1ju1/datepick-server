package app.hdj.datepick.global.annotation;

import app.hdj.datepick.global.annotation.validator.ValueOfEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();

    String[] acceptedValues() default {};

    String message() default "{enumClass} 타입이 아닙니다";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
