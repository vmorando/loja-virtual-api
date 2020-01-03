package com.lojavirtual.livros.api.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lojavirtual.livros.api.entities.Livro;

@Repository
@Transactional(readOnly = true)
	public interface LivroRepository extends CrudRepository<Livro, Long> {
	
	
	Livro findByTituloLivro (String tituloLivro);
	
     List <Livro> findAll() ;

}
