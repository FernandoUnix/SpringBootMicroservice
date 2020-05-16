package com.springbootservicoitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@RibbonClient(name = "servico-produtos")
@SpringBootApplication
public class SpringbootServicoItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoItemApplication.class, args);
	}

}
