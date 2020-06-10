package com.springbootservicoconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SpringbootServicoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoConfigServerApplication.class, args);
	}
	

}
