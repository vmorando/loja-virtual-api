package com.lojavirtual.livros.api.services;

import java.util.Optional;
import com.lojavirtual.livros.api.entities.Cliente;

public interface ClienteService {
	
	/**
	 * Persistir um cliente na base de dados.
	 * 
	 * @param cliente
	 * @return
	 */
	
	Cliente persistir(Cliente cliente);
	
	/**
	 * Buscar cliente por CPF
	 * 
	 * @param cpfCliente
	 * @return
	 */
	
	
	Optional <Cliente> buscarPorCpfCliente(String cpfCliente);
	 
	/**
	 * Buscar cliente por Nome
	 * 
	 * @param nomeCliente
	 * @return
	 */
	
	
	Optional <Cliente> buscarPorNomeCliente(String nomeCliente);
	
	
	/**
	 * Remove um Cliente da base de dados.
	 * 
	 * @param idCliente
	 */
	void remover(Long idCliente);
	 

}
