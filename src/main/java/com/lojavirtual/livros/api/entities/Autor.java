package com.lojavirtual.livros.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Autor")
public class Autor  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idAutor;
	private String nomeAutor;
	private String cpfAutor;

	
	public Autor() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_Autor")
	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}
	
	@Column(name = "Nome_autor")
	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	@Column(name = "Cpf_autor")
	public String getCpfAutor() {
		return cpfAutor;
	}

	public void setCpfAutor(String cpfAutor) {
		this.cpfAutor = cpfAutor;
	}
	

		
}