package app.hdj.datepick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DatepickServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatepickServerApplication.class, args);
    }

}
