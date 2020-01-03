package com.lojavirtual.livros.api.services;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lojavirtual.livros.api.entities.Livro;
import com.lojavirtual.livros.api.repository.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LivroServiceTest {

	@MockBean
	private LivroRepository livroRepository;

	@Autowired
	private LivroService livroService;

	private static final String TituloLivro = "A Cabana";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.livroRepository.findByTituloLivro (Mockito.anyString())).willReturn(new Livro());
		BDDMockito.given(this.livroRepository.save(Mockito.any(Livro.class))).willReturn(new Livro());
	}

	@Test
	public void testBuscarEmpresaPorCnpj() {
		Optional<Livro> empresa = this.livroService.BuscarPorTituloLivro(TituloLivro);

		assertTrue(empresa.isPresent());
	}
	
	@Test
	public void testPersistirLivro() {
		Livro livro = this.livroService.persistir(new Livro());

		assertNotNull(livro);
	}

}
