package com.springbootservicoitem.models.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.springbootservicoitem.clientes.IProdutoClienteRest;
import com.springbootservicoitem.models.Item;
import com.springbootservicoitem.models.Produto;

@Service("serviceFeign")
//@Primary usar o primary ou o value no @service
public class ItemServiceFeign implements IItemService {

	@Autowired
	private IProdutoClienteRest clientesRest;

	@Override
	public List<Item> findAll() {
		return clientesRest.listar().stream().map(produto -> new Item(produto, 1)).collect(Collectors.toList());

	}

	@Override
	public Item findById(Long id, Integer quantidade) {
		Produto produto = clientesRest.getProduto(id);
		return new Item(produto, quantidade);
	}

	@Override
	public Produto save(Produto produto) {
		return clientesRest.criar(produto);
	}

	@Override
	public Produto update(Produto produto, Long id) {
		return clientesRest.editar(produto, id);
	}

	@Override
	public void eliminar(Long id) {
		clientesRest.eliminar(id);
	}
}
