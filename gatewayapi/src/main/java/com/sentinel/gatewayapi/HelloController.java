package com.sentinel.gatewayapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {

    private final RestTemplate restTemplate;

    @Autowired
    public HelloController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/allow", method = RequestMethod.GET)
    public String allow (@RequestParam(value = "name") String name) {
        String greeting = this.restTemplate.getForObject("http://authservice/auth", String.class);
        return String.format("%s, %s!", greeting, name);
    }
}