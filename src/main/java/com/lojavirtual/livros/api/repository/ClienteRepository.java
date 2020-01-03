package com.lojavirtual.livros.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lojavirtual.livros.api.entities.Cliente;


@Repository
@Transactional(readOnly = true)
	public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
     Cliente findByCpfCliente (String cpfCliente);
	 Cliente findByNomeCliente (String nomeCliente);





}
