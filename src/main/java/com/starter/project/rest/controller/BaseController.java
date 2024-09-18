package com.starter.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/api")
    public String testApi(){
        logger.info("hello world");

        return("Hey!!! this works");
    }
}
