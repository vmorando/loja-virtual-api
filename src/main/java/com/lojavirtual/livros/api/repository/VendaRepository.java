package com.lojavirtual.livros.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lojavirtual.livros.api.entities.Venda;

@Repository
	public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	




}
