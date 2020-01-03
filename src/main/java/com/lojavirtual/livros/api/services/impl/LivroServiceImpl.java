package com.lojavirtual.livros.api.services.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lojavirtual.livros.api.entities.Livro;
import com.lojavirtual.livros.api.repository.LivroRepository;
import com.lojavirtual.livros.api.services.LivroService;


@Service
public class LivroServiceImpl implements LivroService {

	private static final Logger log = LoggerFactory.getLogger(LivroServiceImpl.class);

	@Autowired
	private LivroRepository livroRepository;
	
	@Override
	public Optional<Livro> BuscarPorTituloLivro(String tituloLivro) {
		log.info("Buscando um livro por nome {}", tituloLivro);
		return Optional.ofNullable(livroRepository.findByTituloLivro (tituloLivro));
	}

	@Override
	public Livro persistir(Livro livro) {
		log.info("Persistindo livro: {}", livro);
		return this.livroRepository.save(livro);
	}
	
	public void remover(Long idLivro) {
		log.info("Removendo o lançamento ID {}", idLivro);
		this.livroRepository.deleteById(idLivro);
	}

	@Cacheable("BuscarPorId")
	public Optional<Livro> buscarPorId(Long idLivro) {
		log.info("Buscando um Livro pelo ID {}", idLivro);
		return this.livroRepository.findById(idLivro);
	}
	
	 public List<Livro> buscarPorAltoCustoBene() {
	      List <Livro> livros = livroRepository.findAll();
	      List <Livro> result = livros.stream()
	    		  .filter(livro -> livro.getPrecoLivro() <= 100 )
	    		  .collect(Collectors.toList());
	      
          return result;
	    		  

	  }
	 
	 public List<Livro>buscarPorMedioCustoBene() {
	      List <Livro> livros = livroRepository.findAll();
	      List <Livro> result = livros.stream()
	    		  .filter(livro -> livro.getPrecoLivro() >= 100 && livro.getPrecoLivro() <= 300 )
	    		  .collect(Collectors.toList());
	      
         return result;
	    		  

	  }
	 
	 public List<Livro> buscarPorBaixoCustoBene() {
	      List <Livro> livros = livroRepository.findAll();
	      List <Livro> result = livros.stream()
	    		  .filter(livro -> livro.getPrecoLivro() >= 300 )
	    		  .collect(Collectors.toList());
	      
         return result;
	    		  

	  }
	 
}
	