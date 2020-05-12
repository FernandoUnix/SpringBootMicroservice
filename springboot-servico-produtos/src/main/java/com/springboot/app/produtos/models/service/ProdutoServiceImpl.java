package com.springboot.app.produtos.models.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springboot.app.produtos.models.dao.IProdutoDao;
import com.springboot.app.produtos.models.entity.Produto;

@Service
public class ProdutoServiceImpl implements IProdutoService {

	@Autowired
	private IProdutoDao produtoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Produto> findAll() {
		return (List<Produto>) produtoDao.findAll();
	}

	@Override
	public Produto findByid(Long id) {
		return produtoDao.findById(id).orElse(null);
	}

}