package com.lojavirtual.livros.api.controllers;


import java.text.ParseException;
import org.springframework.web.bind.annotation.RestController;

import com.lojavirtual.livros.api.services.LivroService;
import com.lojavirtual.livros.api.dtos.LivroDto;
import com.lojavirtual.livros.api.entities.Livro;
import com.lojavirtual.livros.api.response.Response;

import java.security.NoSuchAlgorithmException;
import java.util.List;
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
@RequestMapping("api/livros")
@CrossOrigin(origins = "*")
public class LivroController {
	
	private static final Logger log = LoggerFactory.getLogger(LivroController.class);

	@Autowired
	private LivroService livroService;	
	
	
	public LivroController() {
		
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
	public ResponseEntity<Response<LivroDto>> cadastrar(@Valid @RequestBody LivroDto livroDto,
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando Livro: {}", livroDto.toString());
		Response<LivroDto> response = new Response<LivroDto>();

		validarDadosExistentes(livroDto, result);
		Livro livro = this.converterDtoParaLivro(livroDto);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro Livro: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.livroService.persistir(livro);
		

		response.setData(this.converterLivroDto(livro));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/baixoCusto")
	public  List<Livro> buscarBaixoCusto () {
		List<Livro> livro = livroService.buscarPorBaixoCustoBene();
		return livro;
	}
	
	@GetMapping(value = "/medioCusto")
	public  List<Livro> buscarMedioCusto () {
		List<Livro> livro = livroService.buscarPorMedioCustoBene();
		return livro;
	}
	
	@GetMapping(value = "/altoCusto")
	public  List<Livro> buscarAltoCusto () {
		List<Livro> livro = livroService.buscarPorAltoCustoBene();
		return livro;
	}
	
	
	/**
	 * Retorna um livro por Titulo.
	 * 
	 * @param tituloLivro
	 * @return ResponseEntity<Response<LivroDto>>
	 */
		
@GetMapping(value = "/tituloLivro/{tituloLivro}")
	public ResponseEntity<Response<LivroDto>> buscarPorTitulo(@PathVariable("tituloLivro") String tituloLivro) {
		log.info("Buscando livro por Titulo: {}", tituloLivro);
		Response<LivroDto> response = new Response<LivroDto>();
		Optional<Livro> livro = livroService.BuscarPorTituloLivro(tituloLivro);

		if (!livro.isPresent()) {
			log.info("Não encontrado resultado para o livro: {}", tituloLivro);
			response.getErrors().add("Não encontrado resultado para o livro: " + tituloLivro);
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterLivroDto(livro.get()));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Remove um lançamento por ID.
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Lancamento>>
	 */
	@DeleteMapping(value = "/{idLivro}")
	public ResponseEntity<Response<String>> remover(@PathVariable("idLivro") Long idLivro) {
		log.info("Removendo livro: {}", idLivro);
		Response<String> response = new Response<String>();
		Optional<Livro> livro = this.livroService.buscarPorId(idLivro);

		if (!livro.isPresent()) {
			log.info("Erro ao remover o Livro ID: {} ser inválido.", idLivro);
			response.getErrors().add("Erro ao remover lançamento. Registro não encontrado para o id " + idLivro);
			return ResponseEntity.badRequest().body(response);
		}

		this.livroService.remover(idLivro);
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
	
	@PutMapping(value = "/{idLivro}")
	public ResponseEntity<Response<LivroDto>> atualizar(@PathVariable("idLivro") Long idLivro,
			@Valid @RequestBody LivroDto livroDto, BindingResult result) throws ParseException {
		log.info("Atualizando Livro: {}", livroDto.toString());
		Response<LivroDto> response = new Response<LivroDto>();
		validarLivro(livroDto, result);
		livroDto.setIdLivro(idLivro);
		Livro livro = this.converterDtoParaLivro(livroDto);

		
		if (result.hasErrors()) {
			log.error("Erro validando livro: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		livro = this.livroService.persistir(livro);
		response.setData(this.converterLivroDto(livro));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Converte os dados do DTO para livro.
	 * 
	 * @param livroDto
	 * @return Livro
	 */
	private Livro converterDtoParaLivro(LivroDto livroDto) {
		Livro livro = new Livro();
		livro.setIdLivro(livroDto.getIdLivro());
		livro.setTituloLivro(livroDto.getTituloLivro());
		livro.setNomeAutor(livroDto.getNomeAutor());
		livro.setNomeEditora(livroDto.getNomeEditora());
		livro.setPrecoLivro(livroDto.getPrecoLivro());
		livro.setGenero(livroDto.getGenero());
		livro.setQtdEstoque(livroDto.getQtdEstoque());

		return livro;
	}

	/**
	 * Popula o DTO de livro com os dados do livro.
	 * 
	 * @param livro
	 * @return LivroDto
	 */
	private LivroDto converterLivroDto(Livro livro) {
		LivroDto livroDto = new LivroDto();
		livroDto.setIdLivro(livro.getIdLivro());
		livroDto.setTituloLivro(livro.getTituloLivro());
		livroDto.setNomeAutor(livro.getNomeAutor());
		livroDto.setNomeEditora(livro.getNomeEditora());
		livroDto.setPrecoLivro(livro.getPrecoLivro());
		livroDto.setGenero(livro.getGenero());
		livroDto.setQtdEstoque(livro.getQtdEstoque());

		return livroDto;
	}
	
	/**
	 * Verifica se o livro já existem na base de dados.
	 * 
	 * @param LivroDto
	 * @param result
	 */
	private void validarDadosExistentes(LivroDto livroDto, BindingResult result) {
		this.livroService.BuscarPorTituloLivro(livroDto.getTituloLivro())
				.ifPresent(emp -> result.addError(new ObjectError("livro", "Livro já existente.")));

	}
	
	/**
	 * Valida um funcionário, verificando se ele é existente e válido no
	 * sistema.
	 * 
	 * @param lancamentoDto
	 * @param result
	 */
	private void validarLivro(LivroDto livroDto, BindingResult result) {
		if (livroDto.getIdLivro() == null) {
			result.addError(new ObjectError("livro", "Livro não informado."));
			return;
		}

		log.info("Validando livro id {}: ", livroDto.getIdLivro());
		Optional<Livro> livro = this.livroService.buscarPorId(livroDto.getIdLivro());
		if (!livro.isPresent()) {
			result.addError(new ObjectError("livro", "Livro não encontrado. ID inexistente."));
		}
	}
	
}
