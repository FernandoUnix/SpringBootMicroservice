package com.springbootservicoeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringbootServicoEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoEurekaServerApplication.class, args);
	}

}
