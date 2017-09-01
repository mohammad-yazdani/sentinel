package com.sentinel.gatewayapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
@EnableWebMvc
public class GatewayapiApplication {

	private static Logger log = LoggerFactory.getLogger(GatewayapiApplication.class);

	public static void main(String[] args) {
		new SpringApplicationBuilder(GatewayapiApplication.class)
				.run(args);

		log.info("");
	}
}
