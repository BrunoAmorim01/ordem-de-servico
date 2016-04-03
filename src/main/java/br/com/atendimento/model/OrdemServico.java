package br.com.atendimento.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "ordem_servico")
public class OrdemServico extends GenericModel {

	@Column(length = 30, nullable = true)
	private String processo;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_os", nullable = false)
	private Date dataOS;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_da_baixa")
	private Date dataBaixa;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_contribuinte", nullable = false)
	private Contribuinte contribuinte;

	@NotBlank
	@Column(name = "obs_os", columnDefinition = "text", nullable = false)
	private String observacoes;

	@ManyToOne
	@JoinColumn(name = "id_fiscal", nullable = true)
	private Colaborador colaborador;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_funcionario", nullable = false)
	private Funcionario funcionario;

	@NotNull
	@OneToMany(mappedBy = "ordemServico", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrdemServicoEndereco> servicoEndereco = new ArrayList<>();

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
	@JoinTable(name = "os_categoria", joinColumns = @JoinColumn(name = "id_os"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))
	private List<Categoria> categorias;

	@Temporal(TemporalType.DATE)
	@Column(name = "programado_para")
	private Date programadoPara;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status_os", nullable = false, length = 12)
	private StatusOrdemServico statusOrdemServico;

	@Transient
	public boolean isNovo() {
		return getCodigo() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}

	@Transient
	public boolean isAberto() {
		return StatusOrdemServico.ABERTO.equals(getStatusOrdemServico());
	}

	@Transient
	public boolean isDesignado() {
		return StatusOrdemServico.DESIGNADO.equals(getStatusOrdemServico());
	}

	@Transient
	public boolean isCancelado() {
		return StatusOrdemServico.CANCELADO.equals(getStatusOrdemServico());
	}
	
	@Transient
	public boolean isConcluido() {		
		return StatusOrdemServico.CONCLUIDO.equals(statusOrdemServico);
	}

	@Transient
	public boolean isRecebido() {

		return StatusOrdemServico.RECEBIDO.equals(statusOrdemServico);
	}

	@Transient
	public boolean isNaoEditavel() {
		return isAberto() || isDesignado();
	}

	@Transient
	public boolean isEditavel() {
		return !isNaoEditavel();
	}

	@Transient
	public boolean isNaoCancelavel() {
		return ((isExistente() && isCancelado()) || isDesignado());
	}

	@Transient
	public boolean isCancelavel() {
		return !isNaoCancelavel();
	}

	@Transient
	public boolean isNaoAlteravel() {
		return isCancelado() || isRecebido() || isConcluido();
	}	

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public Date getDataOS() {
		return dataOS;
	}

	public void setDataOS(Date dataOS) {
		this.dataOS = dataOS;
	}

	public Contribuinte getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<OrdemServicoEndereco> getServicoEndereco() {
		return servicoEndereco;
	}

	public void setServicoEndereco(List<OrdemServicoEndereco> servicoEndereco) {
		this.servicoEndereco = servicoEndereco;
	}

	// public List<OrdemServicoCategoria> getServicoCategoria() {
	// return servicoCategoria;
	// }
	//
	// public void setServicoCategoria(List<OrdemServicoCategoria>
	// servicoCategoria) {
	// this.servicoCategoria = servicoCategoria;
	// }

	public Date getProgramadoPara() {
		return programadoPara;
	}

	public void setProgramadoPara(Date programadoPara) {
		this.programadoPara = programadoPara;
	}

	public StatusOrdemServico getStatusOrdemServico() {
		return statusOrdemServico;
	}

	public void setStatusOrdemServico(StatusOrdemServico statusOrdemServico) {
		this.statusOrdemServico = statusOrdemServico;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
