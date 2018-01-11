package com.sentinel.authservice;

import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.DAO.UserDAOImpl;
import com.sentinel.authservice.service.RSA.RSA;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public RSA rsa () {
        return RSA.getInstance();
    }

    @Bean
    public UserDAO userDAO () { return new UserDAOImpl(); }
}
