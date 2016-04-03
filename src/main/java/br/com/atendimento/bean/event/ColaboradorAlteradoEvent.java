package br.com.atendimento.bean.event;

import br.com.atendimento.model.Colaborador;

public class ColaboradorAlteradoEvent {

	private Colaborador colaborador;

	public ColaboradorAlteradoEvent(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

}
