package com.lojavirtual.livros.api.dtos;



import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


public class AutorDto {

	private Long idAutor;
	private String nomeAutor;
	private String cpfAutor;

	
	public AutorDto() {
		
	}

	public Long getIdAutor(){
        return idAutor;		
	}
	
	public void setIdAutor(Long idAutor) {
		  this.idAutor = idAutor;
	}
	
	@NotEmpty(message = "Titulo não pode ser vazio")
	@Length(min=3, max=200, message = "Titulo deve conter entre 3 e 200 caracteres")
	public String getNomeAutor() {
		return nomeAutor;
		}
	
	public void setNomeAutor(String nomeAutor) {
	    this.nomeAutor = nomeAutor;
	}
	
	public String getCpfAutor () {
		return cpfAutor ;
		}
	
	public void setCpfAutor(String cpfAutor ) {
	    this.cpfAutor = cpfAutor ;
	}
		
	
}
