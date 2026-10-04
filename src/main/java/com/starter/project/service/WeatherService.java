package com.starter.project.service;

import com.starter.project.representations.GeoCodesCollection;
import com.starter.project.representations.GeoCodesResponse;
import com.starter.project.representations.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String GEO_CODES_API = "https://geocoding-api.open-meteo.com/v1/search?name={{city}}&count=1";
    private static final String OPEN_METEO_API = "https://api.open-meteo.com/v1/forecast?latitude={{latitude}}&longitude={{longitude}}&current=temperature_2m";
    private final RestTemplate restTemplate;
    private final RedisService redisService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WeatherService(RestTemplate restTemplate, RedisService redisService, RedisTemplate<Object, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisService = redisService;
    }

    public WeatherResponse getWeather(String city) {
        WeatherResponse cache = redisService.get("weather_of_" + city, WeatherResponse.class);

        if(cache != null) {
            logger.info("Found in cache");
            return cache;
        }

        GeoCodesResponse geoCodes  = getGeoCodes(city);
        String url = OPEN_METEO_API.replace("{{latitude}}", String.valueOf(geoCodes.getLatitude()))
                .replace("{{longitude}}", String.valueOf(geoCodes.getLongitude()));

        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
        redisService.set("weather_of_" + city,response.getBody(), 200l);

        return response.getBody();
    }

    public GeoCodesResponse getGeoCodes(String city){
        String url = GEO_CODES_API.replace("{{city}}", city);
        ResponseEntity<GeoCodesCollection> response = restTemplate.exchange(url, HttpMethod.GET, null, GeoCodesCollection.class);

        if(response.getBody() == null || response.getBody().getResults().isEmpty()) {
            throw new RuntimeException("City not found");
        }

        return response.getBody().getResults().getFirst();
    }
}
