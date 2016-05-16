package br.com.atendimento.DAO;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.com.atendimento.model.Colaborador;
import br.com.atendimento.model.Empresa;

@SuppressWarnings("serial")
@RequestScoped
public class ColaboradorDAO extends GenericDAO<Colaborador> implements Serializable {

	public List<Colaborador> listColaboradorPorempresa(Empresa empresa) {

		String consulta = "SELECT c FROM Colaborador c JOIN FETCH c.empresa e where e.codigo = :codigo   ";

		TypedQuery<Colaborador> query = manager.createQuery(consulta, Colaborador.class);
		query.setParameter("codigo", empresa.getCodigo());

		List<Colaborador> resultado = query.getResultList();

		return resultado;

	}

	// public void remove2(Colaborador t) {
	// try {
	// Object id = getValorDoId(t);
	// Colaborador colaboradorEncontrado = porIdLazy(id);
	//
	// manager.remove(colaboradorEncontrado);
	// manager.remove(colaboradorEncontrado.getUsuario());
	// manager.flush();
	//
	// } catch (PersistenceException exception) {
	// new NegocioException("Não foi possivel excluir o coladorador");
	// }
	// }
	//
	// @Override
	// public Colaborador merge(Colaborador t) {
	// manager.merge(t.getUsuario());
	// return manager.merge(t);
	// }

}
