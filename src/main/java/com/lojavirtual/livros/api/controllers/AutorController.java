package com.lojavirtual.livros.api.controllers;


import java.text.ParseException;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.livros.api.services.AutorService;
import com.lojavirtual.livros.api.dtos.AutorDto;
import com.lojavirtual.livros.api.entities.Autor;
import com.lojavirtual.livros.api.response.Response;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/autores")
@CrossOrigin(origins = "*")
public class AutorController {
	
	private static final Logger log = LoggerFactory.getLogger(AutorController.class);

	@Autowired
	private AutorService autorService;	
	
	
	public AutorController() {
		
		}

	/**
	 * Cadastra um Livro no sistema.
	 * 
	 * @param LivroDto
	 * @param result
	 * @return ResponseEntity<Response<LivroDto>>
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<AutorDto>> cadastrar(@Valid @RequestBody AutorDto autorDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Livro: {}", autorDto.toString());
		Response<AutorDto> response = new Response<AutorDto>();

		validarDadosExistentes(autorDto, result);
		Autor autor = this.converterDtoParaAutor(autorDto);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro Livro: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.autorService.persistir(autor);
		

		response.setData(this.converterAutorDto(autor));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Retorna um livro por Titulo.
	 * 
	 * @param tituloLivro
	 * @return ResponseEntity<Response<LivroDto>>
	 */
		
@GetMapping(value = "/nomeAutor/{nomeAutor}")
	public ResponseEntity<Response<AutorDto>> buscarPorTitulo(@PathVariable("nomeAutor") String nomeAutor) {
		log.info("Buscando livro por Titulo: {}", nomeAutor);
		Response<AutorDto> response = new Response<AutorDto>();
		Optional<Autor> autor = autorService.buscarPorNomeAutor(nomeAutor);

		if (!autor.isPresent()) {
			log.info("Não encontrado resultado para o livro: {}", nomeAutor);
			response.getErrors().add("Não encontrado resultado para o livro: " + nomeAutor);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterAutorDto(autor.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Remove um lançamento por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Lancamento>>
	 */
	@DeleteMapping(value = "/{idAutor}")
	public ResponseEntity<Response<String>> remover(@PathVariable("idAutor") Long idAutor) {
		log.info("Removendo livro: {}", idAutor);
		Response<String> response = new Response<String>();
		Optional<Autor> autor = this.autorService.buscarPorId(idAutor);

		if (!autor.isPresent()) {
			log.info("Erro ao remover o Livro ID: {} ser inválido.", idAutor);
			response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + idAutor);
			return ResponseEntity.badRequest().body(response);
		}

		this.autorService.remover(idAutor);
		return ResponseEntity.ok(new Response<String>());
	}

	/**
	 * Atualiza os dados de um livro.
	 * 
	 * @param idLivro
	 * @param laivroDto
	 * @return ResponseEntity<Response<Livro>>
	 * @throws ParseException 
	 */
	
	@PutMapping(value = "/{idAutor}")
	public ResponseEntity<Response<AutorDto>> atualizar(@PathVariable("idAutor") Long idAutor,
			@Valid @RequestBody AutorDto autorDto, BindingResult result) throws ParseException {
		log.info("Atualizando Autor: {}", autorDto.toString());
		Response<AutorDto> response = new Response<AutorDto>();
		validarAutor(autorDto, result);
		autorDto.setIdAutor(idAutor);
		Autor autor = this.converterDtoParaAutor(autorDto);

		
		if (result.hasErrors()) {
			log.error("Erro validando livro: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		autor = this.autorService.persistir(autor);
		response.setData(this.converterAutorDto(autor));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Converte os dados do DTO para autor.
	 * 
	 * @param autorDto
	 * @return Autor
	 */
	private Autor converterDtoParaAutor(AutorDto autorDto) {
		Autor autor = new Autor();
		autor.setIdAutor(autorDto.getIdAutor());
		autor.setNomeAutor(autorDto.getNomeAutor());
		autor.setCpfAutor(autorDto.getCpfAutor());

		return autor;
	}

	/**
	 * Popula o DTO de autor com os dados do autor.
	 * 
	 * @param autor
	 * @return AutorDto
	 */
	private AutorDto converterAutorDto(Autor autor) {
		AutorDto autorDto = new AutorDto();
		autorDto.setIdAutor(autor.getIdAutor());
		autorDto.setNomeAutor(autor.getNomeAutor());
		autorDto.setCpfAutor(autor.getCpfAutor());

		return autorDto;
	}
	
	/**
	 * Verifica se o autor já existem na base de dados.
	 * 
	 * @param AutorDto
	 * @param result
	 */
	private void validarDadosExistentes(AutorDto autorDto, BindingResult result) {
		this.autorService.buscarPorNomeAutor(autorDto.getNomeAutor())
				.ifPresent(emp -> result.addError(new ObjectError("autor", "Autor já existente.")));

	}
	
	/**
	 * Valida um autor, verificando se ele é existente e válido no
	 * sistema.
	 * 
	 * @param autorDto
	 * @param result
	 */
	private void validarAutor(AutorDto autorDto, BindingResult result) {
		if (autorDto.getIdAutor() == null) {
			result.addError(new ObjectError("autor", "Autor não informado."));
			return;
		}

		log.info("Validando autor id {}: ", autorDto.getIdAutor());
		Optional<Autor> autor = this.autorService.buscarPorId(autorDto.getIdAutor());
		if (!autor.isPresent()) {
			result.addError(new ObjectError("autor", "Autor não encontrado. ID inexistente."));
		}
	}
	
}
