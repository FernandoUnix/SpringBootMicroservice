package com.springboot.app.produtos.models.dao;

import org.springframework.data.repository.CrudRepository;
import com.springboot.service.commons.models.Produto;

public interface IProdutoDao extends CrudRepository<Produto, Long> {

}
