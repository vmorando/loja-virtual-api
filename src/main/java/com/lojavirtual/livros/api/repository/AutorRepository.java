package com.lojavirtual.livros.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lojavirtual.livros.api.entities.Autor;


@Repository
@Transactional(readOnly = true)
	public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	 Autor findByNomeAutor (String nomeAutor);
	 Autor findByCpfAutor (String cpfAutor);



}
