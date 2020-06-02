package com.springbootservicoitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableCircuitBreaker
//@RibbonClient(name = "servico-produtos") gerenciado pelo eureka
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootServicoItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootServicoItemApplication.class, args);
	}

}
