package com.starter.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

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
