package com.lojavirtual.livros.api.dtos;



import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;


public class ClienteDto {

	private Long idCliente;
	private String nomeCliente;
	private String cpfCliente;
	private String telefoneCliente;
	private String emailCliente;
	
	public ClienteDto() {
		
	}

	public Long getIdCliente(){
        return idCliente;		
	}
	
	public void setIdCliente(Long idCliente) {
		  this.idCliente = idCliente;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min=3, max=200, message = "Nome deve conter entre 3 e 200 caracteres")
	public String getNomeCliente() {
		return nomeCliente;
		}
	
	public void setNomeCliente(String nomeCliente) {
	    this.nomeCliente = nomeCliente;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio")
	@Length(min=10, max=10, message = "Cpf deve conter 10 caracteres")
	public String getCpfCliente() {
		return cpfCliente;
		}
	
	public void setCpfCliente(String cpfCliente) {
	    this.cpfCliente = cpfCliente;
	}
		
	public String getTelefoneCliente () {
		return telefoneCliente ;
		}
	
	public void setTelefoneCliente(String telefoneCliente ) {
	    this.telefoneCliente  = telefoneCliente ;
	}
	
	public String getNomeAutor () {
		return emailCliente;
		}
	
	public void setEmailCliente(String emailCliente ) {
	    this.emailCliente  = emailCliente ;
	}


}
