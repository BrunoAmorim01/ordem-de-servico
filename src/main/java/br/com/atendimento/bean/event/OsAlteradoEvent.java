package br.com.atendimento.bean.event;

import br.com.atendimento.model.OrdemServico;

public class OsAlteradoEvent {
	
	private OrdemServico ordemServico;
	
	public OsAlteradoEvent(OrdemServico ordemServico) {
		this.ordemServico=ordemServico;
	}

	public OrdemServico getOrdemServico() {
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

}
