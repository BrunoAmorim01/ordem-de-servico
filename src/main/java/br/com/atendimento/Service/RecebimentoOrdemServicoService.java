package br.com.atendimento.Service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
public class RecebimentoOrdemServicoService implements Serializable {

	@Inject
	OrdemServicoDAO dao;

	@Transactional
	public void ReceberOrdemServico(List<OrdemServico> ordemServicos) {
		ordemServicos.forEach(os -> dao.setToRecebido(os));
	}
}
