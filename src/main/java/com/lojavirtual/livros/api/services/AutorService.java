package com.lojavirtual.livros.api.services;

import java.util.Optional;

import com.lojavirtual.livros.api.entities.Autor;


public interface AutorService {
	
	/**
	 * Persistir um autor na base de dados.
	 * 
	 * @param autor
	 * @return
	 */
	
	Autor persistir(Autor autor);
	
	/**
	 * Buscar autor por CPF
	 * 
	 * @param cpfAutor
	 * @return
	 */
	
	Optional<Autor> buscarPorId(Long idAutor);
	
	/**
	 * Buscar autor por CPF
	 * 
	 * @param cpfAutor
	 * @return
	 */
	
	
	
	Optional <Autor> buscarPorCpfAutor(String cpfAutor);
	 
	/**
	 * Buscar autor por Nome
	 * 
	 * @param nomeAutor
	 * @return
	 */
	
	
	Optional <Autor> buscarPorNomeAutor(String nomeAutor);
	
	
	/**
	 * Remove um Autor da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long idAutor);
	 

}
