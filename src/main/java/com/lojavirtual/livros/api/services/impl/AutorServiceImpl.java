package com.lojavirtual.livros.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lojavirtual.livros.api.entities.Autor;
import com.lojavirtual.livros.api.repository.AutorRepository;
import com.lojavirtual.livros.api.services.AutorService;


@Service
public class AutorServiceImpl implements AutorService {

	private static final Logger log = LoggerFactory.getLogger(AutorServiceImpl.class);

	@Autowired
	private AutorRepository autorRepository;

	@Override
	public Optional<Autor> buscarPorCpfAutor(String cpfAutor) {
		log.info("Buscando um autor por cpf {}", cpfAutor);
		return Optional.ofNullable(autorRepository.findByCpfAutor (cpfAutor));
	}
	
	@Override
	public Optional<Autor> buscarPorNomeAutor(String nomeAutor) {
		log.info("Buscando um autor por nome {}", nomeAutor);
		return Optional.ofNullable(autorRepository.findByNomeAutor (nomeAutor));
	}

	@Override
	public Autor persistir(Autor autor) {
		log.info("Persistindo autor: {}", autor);
		return this.autorRepository.save(autor);
	}

	public void remover(Long idAutor) {
		log.info("Removendo o lançamento ID {}", idAutor);
		this.autorRepository.deleteById(idAutor);
	}

	@Cacheable("BuscarPorId")
	public Optional<Autor> buscarPorId(Long idAutor) {
		log.info("Buscando um Livro pelo ID {}", idAutor);
		return this.autorRepository.findById(idAutor);
	}
}
