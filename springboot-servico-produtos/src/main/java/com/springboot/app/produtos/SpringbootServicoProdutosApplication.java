package com.springboot.app.produtos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.springboot.service.commons.models"})
public class SpringbootServicoProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoProdutosApplication.class, args);
	}
}
