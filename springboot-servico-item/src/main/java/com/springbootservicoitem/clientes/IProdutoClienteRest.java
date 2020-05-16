package com.springbootservicoitem.clientes;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springbootservicoitem.models.Produto;

@FeignClient(name = "servico-produtos")
public interface IProdutoClienteRest {

	@GetMapping("/listar")
	public List<Produto> listar();
	
	@GetMapping("/listar/{id}")
	public Produto getProduto(@PathVariable Long id);
	
}
