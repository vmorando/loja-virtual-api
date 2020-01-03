package com.lojavirtual.livros.api.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name= "Livro")
public class Livro  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idLivro;
	private String tituloLivro;
	private String nomeEditora;
	private Double precoLivro;
	private Integer qtdEstoque;
	private String genero;
	private String nomeAutor;
	
	
	public Livro() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_livro")
	public Long getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}

	@Column(name = "titulo_livro")
	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}

	@Column(name = "preco_livro")
	public Double getPrecoLivro() {
		return precoLivro;
	}

	public void setPrecoLivro(Double precoLivro) {
		this.precoLivro = precoLivro;
	}

	@Column(name = "Qtd_estoque")
	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	@Column(name = "genero")
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Column(name = "Nome_autor")
	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}
	
	@Column(name = "Nome_editora")
	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}
	
	
	@Override
	public String toString() {
		return "Livro [idLivro=" + idLivro + ", tituloLivro=" + tituloLivro + ", nomeEditora=" + nomeEditora + ", precoLivro=" + precoLivro
				+ ", qtdEstoque=" + qtdEstoque + ", genero=" + genero + ", nomeAutor=" + nomeAutor
				+ "]";
	}

}
