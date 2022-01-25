package app.hdj.datepick.global.enums.converter;

import app.hdj.datepick.global.enums.Gender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class GenderFromStringConverter implements Converter<String, Gender> {

    @Override
    public Gender convert(String source) {
        return Gender.fromString(source);
    }

}
