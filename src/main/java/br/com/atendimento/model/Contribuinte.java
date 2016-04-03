package br.com.atendimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class Contribuinte extends GenericModel {

	@NotBlank
	@Column(name = "nome_solicitante", nullable = false)
	private String nome;

	@NotBlank
	@Column(name = "tel_solicitante", nullable = false)
	private String telefone;

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

}
