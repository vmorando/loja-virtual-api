package com.lojavirtual.livros.api.repository;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lojavirtual.livros.api.entities.Autor;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AutorRepositoryTest {

@Autowired
	private AutorRepository autorRepository;
	
	private static final String CPF = "01234567890";
	private static final String Nome = "George R.R. Martin";
	
	@Before
	public void setUp() throws Exception {
		Autor autor = new Autor();
		autor.setNomeAutor(Nome);
		autor.setCpfAutor(CPF);
		this.autorRepository.save(autor);
		
	}
	
	@After
	public final void tearDown() {
		this.autorRepository.deleteAll();
		
	}
	
	@Test
	public void testBuscaPorCpf() {
		Autor autor = this.autorRepository.findByCpfAutor(CPF);
		
		assertEquals(CPF, autor.getCpfAutor());
		
		
			
	}
	
	@Test
	public void testBuscaPorNomeAutor() {
		Autor autor = this.autorRepository.findByNomeAutor(Nome);
		
		assertEquals(Nome, autor.getNomeAutor());
		
		
			
	}
	
	
	

}
