package br.com.atendimento.Service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.model.StatusOrdemServico;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
@RequestScoped
public class CancelamentoOrdemServicoService implements Serializable {

	@Inject
	OrdemServicoDAO dao;	
	
	@Transactional
	public OrdemServico cancelar(OrdemServico ordemServico){
		
		ordemServico=dao.porID(ordemServico.getCodigo());
		ordemServico.setStatusOrdemServico(StatusOrdemServico.CANCELADO);
		
		return dao.merge(ordemServico);
	}
}
