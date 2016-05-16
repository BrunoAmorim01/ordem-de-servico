package br.com.atendimento.DAO;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.atendimento.model.Contribuinte;

@SuppressWarnings("serial")
@RequestScoped
public class ContribuinteDAO extends GenericDAO<Contribuinte> implements Serializable {

	public List<Contribuinte> porNome(String nome) {
		String consulta = "SELECT c FROM Contribuinte c WHERE lower (c.nome) LIKE :nome";

		TypedQuery<Contribuinte> query = manager.createQuery(consulta, Contribuinte.class);
		query.setParameter("nome", "%" + nome + "%");

		List<Contribuinte> resultado = query.getResultList();

		return resultado;

	}
}
