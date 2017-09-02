package com.sentinel.authservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "basic-auth")
public class BasicAuth {

    private static Logger log = LoggerFactory.getLogger(BasicAuth.class);

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity login (@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {

        log.info("User " + username + " trying to enter with pass " + password + ".", username, password);

        return null;
    }

}
