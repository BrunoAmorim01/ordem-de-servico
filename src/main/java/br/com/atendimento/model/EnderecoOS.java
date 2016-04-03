package br.com.atendimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class EnderecoOS extends GenericModel {

	@Column(name = "os_logradouro", nullable = false, length = 100)
	private String logradouro;

	@ManyToOne
	@JoinColumn(nullable = false, name = "ids_bairro", foreignKey = @ForeignKey(name = "FK_BAIRRO") )
	private Bairro bairro;	

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return logradouro;
	}
}
