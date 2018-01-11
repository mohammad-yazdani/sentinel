package com.sentinel.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Sample {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth() {
        return "Proceed";
    }
}
