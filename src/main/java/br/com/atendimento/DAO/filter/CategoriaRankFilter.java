package br.com.atendimento.DAO.filter;

import java.util.Date;

public class CategoriaRankFilter {
	private Date dataDe;
	private Date datafim;

	private int limite;
	
	private int rank;

	public CategoriaRankFilter() {
		this.limite = 15;
	}

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

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
