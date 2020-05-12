package com.springboot.app.produtos.models.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.app.produtos.models.entity.Produto;
import com.springboot.app.produtos.models.service.IProdutoService;

@RestController
public class ProdutoController {

	@Autowired
	private IProdutoService produtoService;

	@GetMapping("/listar")
	public List<Produto> listar() {
		return produtoService.findAll();
	}
	
	@GetMapping("/listar/{id}")
	public Produto getProduto(@PathVariable Long id) {
		return produtoService.findByid(id);
	}
	
}
