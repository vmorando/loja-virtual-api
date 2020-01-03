package com.lojavirtual.livros.api.services;

import java.util.List;
import java.util.Optional;


import com.lojavirtual.livros.api.entities.Livro;

public interface LivroService {
	
	/**
	 * Retorna um Livro por Nome.
	 * 
	 * @param tituloLivro
	 * @return Optional<Livro>
	 */
	
	Optional<Livro> BuscarPorTituloLivro(String tituloLivro);
	
	/**
	 * Retorna um Livro por ID.
	 * 
	 * @param idLivro
	 * @return Optional<Livro>
	 */
	Optional<Livro> buscarPorId(Long idLivro);

	
	/**
	 * Cadastra um novo livro na base de dados.
	 * 
	 * @param livro
	 * @return Livro
	 */
	
	Livro persistir(Livro livro);
	
	/**
	 * Remove um livro da base de dados.
	 * 
	 * @param id
	 */
	void remover(Long idLivro);

	
	List<Livro> buscarPorBaixoCustoBene();
	
	List<Livro> buscarPorMedioCustoBene();
	
	List<Livro> buscarPorAltoCustoBene();
	
}
