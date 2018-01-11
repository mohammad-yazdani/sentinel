package com.sentinel.authservice.controller;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.model.User;
import com.sentinel.lib.JWT.Jwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class BasicAuth {

    private static Logger log = LoggerFactory.getLogger(BasicAuth.class);

    private final
    UserDAO userDAO;

    private final
    String server = "localhost";
    //String server = "18.218.37.158";

    @Autowired
    public BasicAuth(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin(origins = "http://" + server + ":3000")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity login (@RequestHeader("username") String username,
                                 @RequestHeader("password") String password) {

        User user = userDAO.findByUsername(username);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        // TODO : To add headers
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String url = "http://" + server + ":8080/account";

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("id", username);

        ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class, request);

        if (response.getStatusCodeValue() != 202) {
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        }

        if (user == null) return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        if (BCrypt.checkpw(password, user.getAuth())) {
            String jwt = null;
            try {
                jwt = Jwt.generateToken(user.getUsername());
            } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
                log.error(e.getMessage());
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Access-Control-Expose-Headers", "jwt");
            responseHeaders.set("jwt", jwt);
            return new ResponseEntity<>(response.getBody(), responseHeaders, HttpStatus.ACCEPTED);
        }
        else return ResponseEntity.badRequest()
                .body("Wrong password!");
    }

    @CrossOrigin(origins = "http://" + server + ":3000")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ResponseEntity register (@RequestHeader("username") String username,
                                    @RequestHeader("email") String email,
                                    @RequestHeader("password") String password) {

        try {
            User user = new User(username, email, password);
            try {

                MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
                map.add("username", username);
                map.add("email", email);

                HttpHeaders headers = new HttpHeaders();
                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
                String url = "http://" + server + ":8080/account";
                ResponseEntity<String> response = restTemplate.postForEntity( url, request , String.class );
                if (!response.getStatusCode().is2xxSuccessful())
                    throw new Exception(response.getStatusCodeValue() + " error!");
                userDAO.save(user);
                String jwt = null;
                try {
                    jwt = Jwt.generateToken(user.getUsername());
                } catch (NoSuchAlgorithmException | IOException | InvalidKeySpecException e) {
                    log.error(e.getMessage());
                }
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.set("Access-Control-Expose-Headers", "jwt");
                responseHeaders.set("jwt", jwt);
                return new ResponseEntity<>(response.getBody(), responseHeaders, HttpStatus.ACCEPTED);
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

    @CrossOrigin(origins = "http://" + server + ":3000")
    @RequestMapping(value = "/intercept", method = RequestMethod.GET)
    public ResponseEntity intercept (@RequestHeader("token") String token) {
        try {
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("jwt", Jwt.verifyToken(token).getToken());
            return new ResponseEntity<>(true, responseHeaders, HttpStatus.ACCEPTED);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException | JWTDecodeException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Invalid token.");
        }
    }
}
