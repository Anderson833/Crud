package br.edu.ifrn.crud.dominio;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
@Entity
public class Usuario {

	 @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int id;
	@Column(nullable = false)
	
	 private String nome;
	@Column(nullable = false)
	
	 private String email;
	@Column(nullable = false)
	
	 private String senha;
	@Column(nullable = false)
	
	 private String sexo;
	
	@ManyToOne(optional = false)
	private Profissao profissao;
	
	@ManyToMany
	private List<CursoFormacao> formacoes;
	
	@Transient
	private CursoFormacao cursoFormacao;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Arquivo foto;
	
	
	public List<CursoFormacao> getFormacoes() {
		return formacoes;
	}
	public void setFormacoes(List<CursoFormacao> formacoes) {
		this.formacoes = formacoes;
	}
	public CursoFormacao getCursoFormacao() {
		return cursoFormacao;
	}
	public void setCursoFormacao(CursoFormacao cursoFormacao) {
		this.cursoFormacao = cursoFormacao;
	}
	public int getId() {
		return id;
	}
	public Profissao getProfissao() {
		return profissao;
	}
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Arquivo getFoto() {
		return foto;
	}
	public void setFoto(Arquivo foto) {
		this.foto = foto;
	}
	
	
	 
}
