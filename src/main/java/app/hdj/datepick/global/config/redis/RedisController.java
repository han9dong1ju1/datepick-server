package app.hdj.datepick.global.config.redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController("v1/redis")
public class RedisController {
    //테스트  용
    private final RedisService redisService;

    @GetMapping("/")
    public void test() {
        log.debug((String)redisService.setValue("test", "test"));
        log.debug((String)redisService.setValue("increment", 1L));
        log.debug((String)redisService.incerement("increment"));
    }

}
