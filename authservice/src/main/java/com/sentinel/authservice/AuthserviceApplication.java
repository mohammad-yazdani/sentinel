package com.sentinel.authservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AuthserviceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AuthserviceApplication.class)
				.web(false)
				.run(args);
	}
}
