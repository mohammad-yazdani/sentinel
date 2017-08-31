package com.sentinel.authservice;

import com.sentinel.authservice.RSA.RSA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public RSA rsa () {
        return RSA.getInstance();
    }
}
