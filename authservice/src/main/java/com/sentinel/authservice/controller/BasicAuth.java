package com.sentinel.authservice.controller;

import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.model.User;
import com.sentinel.lib.JWT.Jwt;
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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class BasicAuth {

    private static Logger log = LoggerFactory.getLogger(BasicAuth.class);

    private final
    UserDAO userDAO;

    @Autowired
    public BasicAuth(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login (@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {

        User user = userDAO.findByUsername(username);
        if (user == null) return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        if (BCrypt.checkpw(password, user.getAuth())) {
            String jwt = null;
            try {
                jwt = Jwt.generateToken(user.getUsername());
            } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
                log.error(e.getMessage());
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("jwt", jwt);
            return new ResponseEntity<>(true, responseHeaders, HttpStatus.ACCEPTED);
        }
        else return ResponseEntity.badRequest()
                .body("Wrong password!");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
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

    @RequestMapping(value = "/intercept", method = RequestMethod.GET)
    public ResponseEntity intercept (@RequestHeader("token") String token) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("jwt", Jwt.verifyToken(token).getToken());
            return new ResponseEntity<>(true, responseHeaders, HttpStatus.ACCEPTED);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid token.");
        }
    }
}
