package com.springboot.app.produtos.models.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			//produto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
			produto.setPort(port);
			return produto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/listar/{id}")
	public Produto getProduto(@PathVariable Long id) {
		Produto produto = produtoService.findByid(id);
		produto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		
		return produto;
	}

}
