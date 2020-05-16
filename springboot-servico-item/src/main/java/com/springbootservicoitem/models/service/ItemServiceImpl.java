package com.springbootservicoitem.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.springbootservicoitem.models.Item;
import com.springbootservicoitem.models.Produto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements IItemService {

	@Autowired
	private RestTemplate clientRestTemplate;
	//private final String URL = "http://localhost:8001";
	private final String URL = "http://servico-produtos";
	
	@Override
	public List<Item> findAll() {
		List<Produto> produtos = Arrays.asList(clientRestTemplate.getForObject(URL + "/listar", Produto[].class));
		return produtos.stream().map(produto -> new Item(produto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantidade) {
		
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());

		Produto produto  = clientRestTemplate.getForObject(URL + "/listar/{id}", Produto.class, pathVariable);
		
		return new Item(produto, quantidade);
	}

}
