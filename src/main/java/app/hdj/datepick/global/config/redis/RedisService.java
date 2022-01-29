package app.hdj.datepick.global.config.redis;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public Object setValue(String key, Object value) {
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return operations.get(key);
    }

    public Object getValue(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public Object incerement(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.increment(key);
        return operations.get(key);
    }

}
