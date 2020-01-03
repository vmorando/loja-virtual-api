package com.lojavirtual.livros.api.dtos;



import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


public class LivroDto {

	private Long idLivro ;
	private String tituloLivro;
	private String nomeEditora;
	private Double precoLivro;
	private Integer qtdEstoque ;
	private String genero ;
	private String nomeAutor;
	
	public LivroDto() {
		
	}

	public Long getIdLivro(){
        return idLivro;		
	}
	
	public void setIdLivro(Long idLivro) {
		  this.idLivro = idLivro;
	}
	
	@NotEmpty(message = "Titulo não pode ser vazio")
	@Length(min=3, max=200, message = "Titulo deve conter entre 3 e 200 caracteres")
	public String getTituloLivro() {
		return tituloLivro;
		}
	
	public void setTituloLivro(String tituloLivro) {
	    this.tituloLivro = tituloLivro;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min=3, max=200, message = "Nome deve conter entre 3 e 200 caracteres")
	public String getNomeEditora() {
		return nomeEditora;
		}
	
	public void setNomeEditora(String nomeEditora) {
	    this.nomeEditora = nomeEditora;
	}
	

	public Double getPrecoLivro() {
		return precoLivro;
		}
	
	public void setPrecoLivro(Double precoLivro) {
	    this.precoLivro = precoLivro;
	}
	

	public Integer getQtdEstoque () {
		return qtdEstoque ;
		}
	
	public void setQtdEstoque(Integer qtdEstoque ) {
	    this.qtdEstoque  = qtdEstoque ;
	}
	
	public String getGenero () {
		return genero ;
		}
	
	public void setGenero(String genero ) {
	    this.genero  = genero ;
	}
	
	public String getNomeAutor () {
		return nomeAutor ;
		}
	
	public void setNomeAutor(String nomeAutor ) {
	    this.nomeAutor  = nomeAutor ;
	}
	
	@Override
	public String toString() {
		return "LivroDto [idLivro=" + idLivro + ", tituloLivro=" + tituloLivro + ", nomeEditora=" + nomeEditora + ", precoLivro=" + precoLivro
				+ ", qtdEstoque=" + qtdEstoque + ", genero=" + genero + ", nomeAutor=" + nomeAutor
				+ "]";
	}
	

	

	
	
	
}
