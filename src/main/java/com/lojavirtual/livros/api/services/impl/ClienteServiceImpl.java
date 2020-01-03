package com.lojavirtual.livros.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lojavirtual.livros.api.entities.Cliente;
import com.lojavirtual.livros.api.repository.ClienteRepository;
import com.lojavirtual.livros.api.services.ClienteService;


@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public Optional<Cliente> buscarPorCpfCliente(String cpfCliente) {
		log.info("Buscando um cliente por cpf {}", cpfCliente);
		return Optional.ofNullable(clienteRepository.findByCpfCliente (cpfCliente));
	}
	
	@Override
	public Optional<Cliente> buscarPorNomeCliente(String nomeCliente) {
		log.info("Buscando um cliente por nome {}", nomeCliente);
		return Optional.ofNullable(clienteRepository.findByNomeCliente (nomeCliente));
	}

	@Override
	public Cliente persistir(Cliente cliente) {
		log.info("Persistindo cliente: {}", cliente);
		return this.clienteRepository.save(cliente);
	}

	public void remover(Long idCliente) {
		log.info("Removendo o cliente ID {}", idCliente);
		this.clienteRepository.deleteById(idCliente);
	}

}
