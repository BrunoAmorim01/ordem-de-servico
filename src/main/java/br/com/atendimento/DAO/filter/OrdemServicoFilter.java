package br.com.atendimento.DAO.filter;

import java.util.Date;
import java.util.List;

import br.com.atendimento.model.Categoria;
import br.com.atendimento.model.StatusOrdemServico;

public class OrdemServicoFilter {

	private Date dataDe;
	private Date datafim;
	private Long codigo;
	private String processo;
	private String solicitante;
	private String fiscal;

	private List<Categoria> categorias;
	
//	private  Categoria[] categorias;

	private StatusOrdemServico[] status;

	public Date getDataDe() {
		return dataDe;
	}

	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}

	public Date getDatafim() {
		return datafim;
	}

	public void setDatafim(Date datafim) {
		this.datafim = datafim;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getFiscal() {
		return fiscal;
	}

	public void setFiscal(String fiscal) {
		this.fiscal = fiscal;
	}
	
//	public Categoria[] getCategorias() {
//		return categorias;
//	}
//	
//	public void setCategorias(Categoria[] categorias) {
//		this.categorias = categorias;
//	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public StatusOrdemServico[] getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico[] status) {
		this.status = status;
	}
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
