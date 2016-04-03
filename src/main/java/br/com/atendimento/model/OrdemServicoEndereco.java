package br.com.atendimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "ordem_servico_endereco")
public class OrdemServicoEndereco extends GenericModel {

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_ORDEM_SERVICO_ENDERECO_OS") )
	private OrdemServico ordemServico;

	@ManyToOne
	@JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "FK_ORDEM_SERVICO_ENDERECO_LOGRADOURO") )
	private EnderecoOS endereco;	
	
	@NotBlank
	@Column(name = "referencia_local_os")
	private String referencia;

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public EnderecoOS getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoOS endereco) {
		this.endereco = endereco;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
}
