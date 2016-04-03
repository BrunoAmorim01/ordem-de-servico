package br.com.atendimento.Service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.atendimento.DAO.ContribuinteDAO;
import br.com.atendimento.model.Contribuinte;
import br.com.atendimento.util.jpa.Transactional;

public class ContribuinteService implements Serializable {

	@Inject
	ContribuinteDAO contribuinteDAO;

	@Transactional
	public Contribuinte salvar(Contribuinte contribuinte) {
		return contribuinteDAO.merge(contribuinte);
	}

}
