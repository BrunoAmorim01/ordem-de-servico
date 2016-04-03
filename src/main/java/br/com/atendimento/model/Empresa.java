package br.com.atendimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@SuppressWarnings("serial")
@Entity

public class Empresa  extends GenericModel {
	
	@NotBlank
	@Column(name="nome_Fantasia",nullable=false,length=50)
	private String nomeFantasia;
	
	@NotBlank
	@Column(name="razao_social",nullable=false,length=50)
	private String razaoSocial;
	
	@NotBlank
	@CNPJ
	@Column(nullable=false,length=18)
	private String CNPJ;

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}

}
