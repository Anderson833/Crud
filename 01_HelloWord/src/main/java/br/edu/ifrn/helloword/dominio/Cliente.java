package br.edu.ifrn.helloword.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Cliente implements Serializable{
	

	private static final long SerialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	
	private String nome;
	private String telefone;
	private String descricao;
	
	@OneToMany
	private List<Dados> dados;
	
	public List<Dados> getDados() {
		return dados;
	}
	public void setDados(List<Dados> dados) {
		this.dados = dados;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
