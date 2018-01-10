package com.sentinel.authservice;

import com.sentinel.authservice.DAO.UserDAO;
import com.sentinel.authservice.DAO.UserDAOImpl;
import com.sentinel.authservice.service.RSA.RSA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RSA rsa () {
        return RSA.getInstance();
    }

    @Bean
    public UserDAO userDAO () { return new UserDAOImpl(); }
}
