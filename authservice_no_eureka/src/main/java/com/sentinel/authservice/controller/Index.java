package com.sentinel.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Index {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index () {
        return "Hello!";
    }
}
