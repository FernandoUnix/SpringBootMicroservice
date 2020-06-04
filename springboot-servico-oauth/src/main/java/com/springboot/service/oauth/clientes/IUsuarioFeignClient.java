package com.springboot.service.oauth.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.springboot.app.commons.usuarios.models.Usuario;

@FeignClient(name="servico-usuarios")
public interface IUsuarioFeignClient {

	@GetMapping("/usuarios/search/buscar-username")
	public Usuario findByUsername(@RequestParam String nome);
}
