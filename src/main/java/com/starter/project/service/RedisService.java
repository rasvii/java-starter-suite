package com.starter.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> clazz){
        Object o = redisTemplate.opsForValue().get(key);
        if(o == null){
            return null;
        }
        return readValue(o, clazz);
    }

    public void set(String key, Object o, Long ttl){
        try {
            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(o);

            redisTemplate.opsForValue().set(
                    key,
                    json,
                    ttl,
                    TimeUnit.SECONDS
            );
        } catch (JsonProcessingException e) {
            logger.error("Unable to serialize value", e);
        }
    }


    private <T> T readValue(Object o, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), clazz);
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
