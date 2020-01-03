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

import com.lojavirtual.livros.api.entities.Cliente;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTest {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	private static final String CPF = "54400483113";
	
	@Before
	public void setUp() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNomeCliente("Pedro Henrique Navas");
		cliente.setCpfCliente(CPF);
		this.clienteRepository.save(cliente);
		
	}
	
	@After
	public final void tearDown() {
		this.clienteRepository.deleteAll();
		
	}
	
	@Test
	public void testBuscarPorCpf() {
		Cliente cliente = this.clienteRepository.findByCpfCliente(CPF);
		
		assertEquals(CPF, cliente.getCpfCliente());
		
		
			
	}
	
	
	


}
