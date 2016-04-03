package br.com.atendimento.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "ordem_servico_categoria")
public class OrdemServicoCategoria extends GenericModel {

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_os", foreignKey = @ForeignKey(name = "FK_ORDEM_SERVICO_CATEGORIA_OS") )
	private OrdemServico ordemServico;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_categoria", foreignKey = @ForeignKey(name = "FK_ORDEM_SERVICO_CATEGORIA") )
	private Categoria categoria;

	@Column(name = "metragem_os", precision = 7, scale = 2)
	private Double metragem;

	@Column(name = "qtde_gari")
	private Short qtdeGari;

	@Column(name = "qtde_viagem")
	private Short qtdeViagem;
	
	@Column(name = "cond_tecnica",nullable=true, length = 200)
	private String condicaoTecnica;

	@Temporal(TemporalType.DATE)
	private Date dataInicio;

	@Temporal(TemporalType.DATE)
	private Date dataConclusao;

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Double getMetragem() {
		return metragem;
	}

	public void setMetragem(Double metragem) {
		this.metragem = metragem;
	}

	public Short getQtdeGari() {
		return qtdeGari;
	}

	public void setQtdeGari(Short qtdeGari) {
		this.qtdeGari = qtdeGari;
	}

	public Short getQtdeViagem() {
		return qtdeViagem;
	}

	public void setQtdeViagem(Short qtdeViagem) {
		this.qtdeViagem = qtdeViagem;
	}

	public String getCondicaoTecnica() {
		return condicaoTecnica;
	}

	public void setCondicaoTecnica(String condicaoTecnica) {
		this.condicaoTecnica = condicaoTecnica;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
}
