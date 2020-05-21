package com.springboot.app.produtos.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.produtos.models.entity.Produto;
import com.springboot.app.produtos.models.service.IProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private Environment env;

	@Value("${server.port}")
	private Integer port;

	@Autowired
	private IProdutoService produtoService;

	@GetMapping("/listar")
	public List<Produto> listar() {
		return produtoService.findAll().stream().map(produto -> {
			// produto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			produto.setPort(port);
			return produto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/listar/{id}")
	public Produto getProduto(@PathVariable Long id) {
		Produto produto = produtoService.findById(id);
		produto.setPort(Integer.parseInt(env.getProperty("local.server.port")));

//		// Para teste da configuração timeout do @HystrixCommand
//		 try {
//		 Thread.sleep(8000L);
//		 } catch (InterruptedException e) {
//		 // TODO Auto-generated catch block
//		 e.printStackTrace();
//		 }

		return produto;

	}

	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto criar(@RequestBody Produto produto) {
		return produtoService.save(produto);
	}
	
}
