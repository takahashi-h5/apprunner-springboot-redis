package jp.co.confrage.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class DemoApplication {
    private final RedisTemplate<String, String> redisTemplate;

    DemoApplication(final RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/get")
    public String root() {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
        return "Status: " + ops.get("cache");
    }

	@PostMapping("/post/{data}")
	public String postMethodName(@PathVariable @NonNull final String data) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("cache", data);
        return "Status: " + ops.get("cache");
	}
	
}