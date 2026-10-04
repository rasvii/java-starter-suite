package com.starter.project.rest.controller;

import com.starter.project.representations.WeatherResponse;
import com.starter.project.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WeatherService weatherService;

    @GetMapping(value = "/api/weather", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<WeatherResponse> getWeatherForCity(
            @RequestParam(name = "city", required = true) String city
    ){

        WeatherResponse weatherResponse = weatherService.getWeather(city);
        return new ResponseEntity<>(weatherResponse, HttpStatus.OK);
    }

}
