package com.lojavirtual.livros.api.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.lojavirtual.livros.api.dtos.AutorDto;
import com.lojavirtual.livros.api.entities.Autor;
import com.lojavirtual.livros.api.services.AutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutorControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private AutorService autorService;
	
	private static final String URL_BASE = "/api/autores/";
	private static final Long ID = Long.valueOf(1);
	private static final String CPF = "51463645000100";
	private static final String NOME_AUTOR = "Teste Autor";
	
@Test
	@WithMockUser
	public void testCadastrarAutor() throws Exception {
		Autor autor = obterDadosAutor();
		BDDMockito.given(this.autorService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Autor()));
		BDDMockito.given(this.autorService.persistir(Mockito.any(Autor.class))).willReturn(autor);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.idAutor").value(ID))
				.andExpect(jsonPath("$.data.nomeAutor").value(NOME_AUTOR))
				.andExpect(jsonPath("$.data.cpfAutor").value(CPF))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
@Test
	@WithMockUser
	public void testRemoverAutor() throws Exception {
		BDDMockito.given(this.autorService.buscarPorId(Mockito.anyLong())).willReturn(Optional.of(new Autor()));

		mvc.perform(MockMvcRequestBuilders.delete(URL_BASE + ID)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	


	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		AutorDto AutorDto = new AutorDto();
		AutorDto.setIdAutor(ID);
		AutorDto.setNomeAutor(NOME_AUTOR);
		AutorDto.setCpfAutor(CPF);
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(AutorDto);
	}

	private Autor obterDadosAutor() {
		Autor autor = new Autor();
		autor.setIdAutor(ID);
		return autor;
	}	

}


