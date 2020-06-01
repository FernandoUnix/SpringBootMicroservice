package com.springbootservicoitem.clientes;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springbootservicoitem.models.Produto;

@FeignClient(name = "servico-produtos")
public interface IProdutoClienteRest {

	@GetMapping("/listar")
	public List<Produto> listar();
	
	@GetMapping("/listar/{id}")
	public Produto getProduto(@PathVariable Long id);
	
	
	@PostMapping("/criar")
	public Produto criar(@RequestBody Produto produto);
	
	@PutMapping("/editar/{id}")
	public Produto editar(@RequestBody Produto produto, @PathVariable Long id);
	 
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable Long id);
}
