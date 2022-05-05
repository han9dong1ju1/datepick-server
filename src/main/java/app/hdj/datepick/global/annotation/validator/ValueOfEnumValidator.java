package app.hdj.datepick.global.annotation.validator;

import app.hdj.datepick.global.annotation.ValueOfEnum;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        List<String> acceptedStrings = Arrays.asList(annotation.acceptedValues());
        List<String> enumValues = Stream.of(annotation.enumClass().getEnumConstants())
            .map(Enum::name).map(String::toUpperCase).collect(Collectors.toList());
        if (acceptedStrings.isEmpty()) {
            this.acceptedValues = enumValues;
        } else {
            this.acceptedValues = acceptedStrings.stream()
                .filter(value -> enumValues.contains(value.toUpperCase()))
                .collect(Collectors.toList());
        }
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
            "[" + String.join(", ", acceptedValues) + "] 중 하나의 값이어야 합니다").addConstraintViolation();

        return acceptedValues.contains(value.toString()) || acceptedValues.contains(
            value.toString().toUpperCase());
    }
}