package com.sentinel.gatewayapi.config;

import com.sentinel.gatewayapi.filter.Authentication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

    @Bean
    public Authentication authentication () { return new Authentication(); }

}
