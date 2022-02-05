package br.edu.ifrn.helloword.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.NotFound;

@Entity
public class Dados {

	@Id
	@NotFound
	private String rg;
	@NotFound
	private  String cpf;
	
	
	@ManyToOne(optional = false)
	private Cliente cliente;


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
