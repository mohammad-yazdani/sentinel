package com.sentinel.authservice.controller;

import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.sentinel.lib.JWT;

@RestController
public class BasicAuth {

    private static Logger log = LoggerFactory.getLogger(BasicAuth.class);

    private final
    UserDAO userDAO;

    @Autowired
    public BasicAuth(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity login (@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {

        // TODO : Authenticate
        // TODO : Implement salted-hash passwords

        User user = userDAO.findByUsername(username);

        if (BCrypt.checkpw(password, user.getAuth())) {
            String jwt = ;
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("jwt", jwt);
            return new ResponseEntity<>(user.toString(), responseHeaders, HttpStatus.ACCEPTED);
        }
        else return ResponseEntity.badRequest()
                .body("Wrong password!");
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity register (@RequestHeader("username") String username,
                                    @RequestHeader("email") String email,
                                    @RequestHeader("password") String password) {

        try {
            User user = new User(username, email, password);
            try {
                userDAO.save(user);
                return ResponseEntity.ok("User created.");
            }
            catch (Exception e) {
                log.error(e.getMessage());
                e.getStackTrace();
                return ResponseEntity.badRequest().body("User creation failed.");
            }
        }
        catch (NullPointerException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("User creation failed.");
        }
    }
}
