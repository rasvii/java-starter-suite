package com.starter.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void trial(){
        redisTemplate.opsForValue().set("email", "rasvi24@gmail.com");
        redisTemplate.opsForValue().set("salary", "10k");
        Object email = redisTemplate.opsForValue().get("email");
        int a = 1;
    }


}
