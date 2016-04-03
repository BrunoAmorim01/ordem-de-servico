package br.com.atendimento.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
public class Funcionario extends GenericModel {

	@NotBlank
	@Column(name = "matricula_func", nullable = false)
	private String matricula;

	@NotNull
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_USUARIO") , name = "usuario_id", nullable = false)
	private Usuario usuario;

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
