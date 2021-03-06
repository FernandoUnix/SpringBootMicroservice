package com.springbootservicoitem.models;

import com.springboot.service.commons.models.Produto;
public class Item {

	private Produto produto;
	private Integer quantidade;

	public Item() {
		super();
	}

	public Item(Produto produto, Integer quantidade) {
		super();
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getTotal() {
		return this.produto.getPreco() * this.quantidade.doubleValue();
	}

}
