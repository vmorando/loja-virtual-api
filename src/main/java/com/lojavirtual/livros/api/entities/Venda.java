package com.lojavirtual.livros.api.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Venda")
public class Venda  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idVenda;
	private OffsetDateTime dtVenda;
    private Double valorVenda;
    private String tituloLivro;
    private String nomeCliente;
    
	
	
	public Venda() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id_venda")
	public Long getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Long idVenda) {
		this.idVenda = idVenda;
	}
	
	@Column(name = "dt_venda")
	public OffsetDateTime getDtVenda() {
		return dtVenda;
	}

	public void setDtVenda(OffsetDateTime dtVenda) {
		this.dtVenda = dtVenda;
	}

	@Column(name = "Titulo_livro")
	public String getTituloLivro() {
		return tituloLivro;
	}

	public void setTituloLivro(String tituloLivro) {
		this.tituloLivro = tituloLivro;
	}
	
	@Column(name = "nome_cliente")
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	@Column(name = "valor_venda")
	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}


}
