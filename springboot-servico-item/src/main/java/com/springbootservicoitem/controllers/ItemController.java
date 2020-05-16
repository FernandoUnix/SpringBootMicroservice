package com.springbootservicoitem.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.springbootservicoitem.models.Item;
import com.springbootservicoitem.models.service.IItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceRestTemplate") // tem duas implementações, @Qualifier defini qual usar
	private IItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}

	@GetMapping("/listar/{id}/quantidade/{quantidade}")
	public Item getItem(@PathVariable Long id, @PathVariable Integer quantidade) {
		return itemService.findById(id, quantidade);
	}
}
