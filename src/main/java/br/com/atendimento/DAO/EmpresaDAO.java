package br.com.atendimento.DAO;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.atendimento.exception.NegocioException;
import br.com.atendimento.model.Empresa;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
public class EmpresaDAO extends GenericDAO<Empresa> implements Serializable {
	
//	@Inject
//	private EntityManager manager;
//
//	@Override
//	public Empresa merge(Empresa t) {
//		return manager.merge(t);
//	}
//
//	@Override
//	@Transactional
//	public void remove(Empresa t) {
//		try {
//			t = porID(t.getCodigo());			
//			manager.remove(t);
//			manager.flush();
//		} catch (PersistenceException exception) {
//			new NegocioException("Não foi possivel excluir a empresa");
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Empresa> list() {
//		Session session = manager.unwrap(Session.class);
//		Criteria criteria = session.createCriteria(Empresa.class);
//		return criteria.addOrder(Order.asc("nomeFantasia")).list();
//	}
//
//	@Override
//	public Empresa porID(Long codigo) {
//		return manager.find(Empresa.class, codigo);
//	}

}
