package com.springbootservicoitem.models.service;

import java.util.List;

import com.springbootservicoitem.models.Item;

public interface IItemService {

	public List<Item> findAll();

	public Item findById(Long id, Integer quantidade);

}
