package app.hdj.datepick.global.common.enums.converter;

import app.hdj.datepick.global.common.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class GenderFromStringConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        return Gender.fromString(source);
    }

}
