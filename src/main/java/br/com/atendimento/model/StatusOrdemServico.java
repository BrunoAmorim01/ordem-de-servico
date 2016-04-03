package br.com.atendimento.model;

public enum StatusOrdemServico {
	ABERTO("Aberto"), DESIGNADO("Designado"), RECEBIDO("Recebido"), CONCLUIDO("Concluido"), CANCELADO("Cancelado");

	private String descricao;

	private StatusOrdemServico(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
