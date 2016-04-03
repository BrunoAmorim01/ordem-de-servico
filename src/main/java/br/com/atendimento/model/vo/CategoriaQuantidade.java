package br.com.atendimento.model.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoriaQuantidade implements Serializable {
	
	private String categoria;
	private Long quantidade;
	private Integer limite;
	private int rank;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getLimite() {
		return limite;
	}
	public void setLimite(Integer limite) {
		this.limite = limite;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
}
