package com.toyoda.sura.repository;

import com.toyoda.sura.entity.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
}
