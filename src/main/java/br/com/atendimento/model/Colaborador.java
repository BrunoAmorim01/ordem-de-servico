package br.com.atendimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class Colaborador extends GenericModel {

	@NotBlank
	@Column(name = "serie_identificacao", nullable = false, unique = true, length = 50)
	private String serieIdentificacao;

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_USUARIO_COLABORADOR") , name = "usuario_id", nullable = false)
	private Usuario usuario;

	@NotNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_EMPRESA_COLABORADOR") , name = "empresa_id", nullable = false)
	private Empresa empresa;

	public String getSerieIdentificacao() {
		return serieIdentificacao;
	}

	public void setSerieIdentificacao(String serieIdentificacao) {
		this.serieIdentificacao = serieIdentificacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

}
