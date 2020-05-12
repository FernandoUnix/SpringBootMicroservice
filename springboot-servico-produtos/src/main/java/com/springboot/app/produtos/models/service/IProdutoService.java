package com.springboot.app.produtos.models.service;

import java.util.List;
import com.springboot.app.produtos.models.entity.Produto;

public interface IProdutoService {

	public List<Produto> findAll();

	public Produto findByid(Long id);
}
