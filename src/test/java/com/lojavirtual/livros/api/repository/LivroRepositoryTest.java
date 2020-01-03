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

import com.lojavirtual.livros.api.entities.Livro;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LivroRepositoryTest {
	
	@Autowired
	private LivroRepository livroRepository;
	
	private static final String tituloLivro = "A Guerra dos Tronos";
	
	@Before
	public void setUp() throws Exception {
		Livro livro = new Livro();
		livro.setNomeEditora("LeYa");
		livro.setTituloLivro(tituloLivro);
		this.livroRepository.save(livro);
		
	}
	
	@After
	public final void tearDown() {
		this.livroRepository.deleteAll();
		
	}
	
	@Test
	public void testBuscarPorTituloLivro() {
		Livro livro = this.livroRepository.findByTituloLivro(tituloLivro);
		
		assertEquals(tituloLivro, livro.getTituloLivro());
		
		
			
	}
	
	
	

}
