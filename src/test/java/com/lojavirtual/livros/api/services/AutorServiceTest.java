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

import com.lojavirtual.livros.api.entities.Autor;
import com.lojavirtual.livros.api.repository.AutorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AutorServiceTest {

	@MockBean
	private AutorRepository autorRepository;

	@Autowired
	private AutorService autorService;

	private static final String CpfAutor = "44540013831";
	private static final String NomeAutor = "Lucas Alves";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.autorRepository.findByNomeAutor(Mockito.anyString())).willReturn(new Autor());
		BDDMockito.given(this.autorRepository.findByCpfAutor (Mockito.anyString())).willReturn(new Autor());
		BDDMockito.given(this.autorRepository.save(Mockito.any(Autor.class))).willReturn(new Autor());
	}

	@Test
	public void testBuscarAutorPorCpf() {
		Optional<Autor> autor = this.autorService.buscarPorCpfAutor(CpfAutor);
		

		assertTrue(autor.isPresent());
	}
	
	
	@Test
	public void testBuscarPorNomeAutor() {
		Optional<Autor> autor = this.autorService.buscarPorNomeAutor(NomeAutor);

		assertTrue(autor.isPresent());
	}
	
	@Test
	public void testPersistirLivro() {
		Autor autor = this.autorService.persistir(new Autor());

		assertNotNull(autor);
	}

}
