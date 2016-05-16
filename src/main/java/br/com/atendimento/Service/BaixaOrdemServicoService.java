package br.com.atendimento.Service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
@RequestScoped
public class BaixaOrdemServicoService implements Serializable {

	@Inject
	private OrdemServicoDAO dao;

	@Transactional
	public void baixarDeOrdemServico(List<OrdemServico> ordemServicos) {
		ordemServicos.forEach(dao::setToConcluido);
	}
}
