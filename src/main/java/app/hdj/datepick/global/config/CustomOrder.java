package app.hdj.datepick.global.config;

import org.springframework.core.Ordered;

public interface CustomOrder extends Ordered {

    int CONTROLLER_EXCEPTION_ADVICE = 10;
    int BASE_EXCEPTION_ADVICE = 11;
    int FINAL = 100;

}
