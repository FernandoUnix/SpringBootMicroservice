package com.springboot.app.produtos.models.service;

import java.util.List;
import com.springboot.app.produtos.models.entity.Produto;

public interface IProdutoService {

	public List<Produto> findAll();

	public Produto findById(Long id);

	public Produto save(Produto produto);

	public void deleteById(Long id);
}
