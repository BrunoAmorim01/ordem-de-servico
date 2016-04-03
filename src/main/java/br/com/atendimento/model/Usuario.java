package br.com.atendimento.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class Usuario extends GenericModel {

	@NotBlank
	@Column(name = "nome_usuario", nullable = false, unique = true, length = 80)
	private String nome;

	@NotBlank
	@Email
	@Column(name = "email_usuario", nullable = false, unique = true, length = 100)
	private String email;

	@NotBlank
	@Column(name = "tel_fixo_usuario", length = 13)
	private String telFixo;

	@NotBlank
	@Column(name = "tel_celular_usuario", length = 14, unique = true)
	private String telCelular;

	@NotBlank
	@Column(name = "senha_usuario", nullable = false, length = 32)
	private String senha;

	@Column(name = "ativo", nullable = false)
	private boolean ativo;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "usuario_grupo", joinColumns = { @JoinColumn(name = "usuario_id") }, inverseJoinColumns = {
			@JoinColumn(name = "grupo_id") })
	private List<Grupo> grupos;

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

	public String getTelFixo() {
		return telFixo;
	}

	public void setTelFixo(String telFixo) {
		this.telFixo = telFixo;
	}

	public String getTelCelular() {
		return telCelular;
	}

	public void setTelCelular(String telCelular) {
		this.telCelular = telCelular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
