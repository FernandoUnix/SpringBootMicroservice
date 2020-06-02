package com.springbootservicoitem.models.service;

import java.util.List;

import com.springbootservicoitem.models.Item;
import com.springboot.service.commons.models.Produto;

public interface IItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer quantidade);

	public Produto save(Produto produto);

	public Produto update(Produto produto, Long id);

	public void eliminar(Long id);
}
