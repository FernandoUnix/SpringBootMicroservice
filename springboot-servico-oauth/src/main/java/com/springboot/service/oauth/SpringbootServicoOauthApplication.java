package com.springboot.service.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SpringbootServicoOauthApplication implements CommandLineRunner {

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoOauthApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		String passwordPasswordEncoder = bcryptPasswordEncoder.encode(password);

		System.out.println("password: " + passwordPasswordEncoder);
	}
}
