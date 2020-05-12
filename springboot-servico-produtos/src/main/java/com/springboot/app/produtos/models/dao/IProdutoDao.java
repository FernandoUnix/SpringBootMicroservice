package com.springboot.app.produtos.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.springboot.app.produtos.models.entity.Produto;

public interface IProdutoDao extends CrudRepository<Produto, Long> {

}
