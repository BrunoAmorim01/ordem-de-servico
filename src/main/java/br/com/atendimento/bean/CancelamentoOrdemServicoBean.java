package br.com.atendimento.bean;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.Service.CancelamentoOrdemServicoService;
import br.com.atendimento.bean.event.OsAlteradoEvent;
import br.com.atendimento.bean.event.OsEdicao;
import br.com.atendimento.model.OrdemServico;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CancelamentoOrdemServicoBean implements Serializable{
	
	@Inject
	CancelamentoOrdemServicoService service;
	
	@Inject
	private Event<OsAlteradoEvent> osAlteradoEvent;
	
	@Inject
	@OsEdicao
	OrdemServico ordemServico;
	
	public void cancelar(){
		ordemServico=service.cancelar(ordemServico);
		
		osAlteradoEvent.fire(new OsAlteradoEvent(ordemServico));
		
		Messages.addGlobalInfo("Ordem de serviço cancelada com sucesso");
	}

}
