package com.springboot.app.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.springboot.app.commons.usuarios.models"})

public class SpringbootServicoUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoUsuariosApplication.class, args);
	}
}
 