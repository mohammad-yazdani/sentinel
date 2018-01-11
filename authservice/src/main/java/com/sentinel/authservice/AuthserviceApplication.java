package com.sentinel.authservice;

import com.sentinel.authservice.service.RSA.RSA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
//@EnableFeignClients
public class AuthserviceApplication {

	private static Logger log = LoggerFactory.getLogger(RSA.class);

	@Autowired
	public AuthserviceApplication() {
		// RSA rsa = RSA.getInstance();
		// if (rsa.GenerateKeyPair()) log.info("RSA key pair generated.");
		// else log.error("Could not generate RSA key pair.");
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthserviceApplication.class, args);
	}
}
