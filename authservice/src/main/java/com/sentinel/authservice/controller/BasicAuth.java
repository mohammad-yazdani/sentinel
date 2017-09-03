package com.sentinel.authservice.controller;

import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuth {

    private static Logger log = LoggerFactory.getLogger(BasicAuth.class);

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity login (@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {

        log.info("User " + username + " trying to enter with pass " + password + ".");

        return null;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ResponseEntity register (@RequestHeader("username") String username,
                                    @RequestHeader("email") String email,
                                    @RequestHeader("password") String password) {

        log.info("Register with username: " + username + " email: " + email + " password: " + password);
        try {
            User user = new User(username, email, password);
            if (userDAO.save(user)) {
                return ResponseEntity.ok("User created.");
            }
            else {
                return ResponseEntity.badRequest().body("User creation failed.");
            }
        }
        catch (NullPointerException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("User creation failed.");
        }
    }
}
