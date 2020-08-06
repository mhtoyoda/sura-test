package com.toyoda.sura.repository;

import com.toyoda.sura.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Cliente findByEmail(String email);
}
