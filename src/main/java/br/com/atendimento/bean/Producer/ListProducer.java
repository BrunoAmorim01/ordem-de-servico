package br.com.atendimento.bean.Producer;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.atendimento.model.Empresa;
import br.com.atendimento.model.Grupo;

@RequestScoped
public class ListProducer {

	@Inject
	EntityManager manager;

	@Produces
	public List<Grupo> grupos() {
		return manager.createQuery("SELECT g FROM Grupo g", Grupo.class).getResultList();
	}

	@Produces
	@RequestScoped
	public List<Empresa> empresas() {
		return manager.createQuery("SELECT e FROM Empresa e", Empresa.class).getResultList();
	}
}
