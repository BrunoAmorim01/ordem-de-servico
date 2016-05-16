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
public class OrdemServicoService implements Serializable {

	@Inject
	OrdemServicoDAO ordemServicoDAO;

	@Transactional
	public OrdemServico save(OrdemServico ordemServico) {
		if(ordemServico.getColaborador()!=null){
			System.err.println("designado");
			ordemServico.setStatusOrdemServico(StatusOrdemServico.DESIGNADO);
		}else{
			ordemServico.setStatusOrdemServico(StatusOrdemServico.ABERTO);
		}
		return ordemServicoDAO.merge(ordemServico);
	}
}
