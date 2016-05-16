package br.com.atendimento.DAO;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import br.com.atendimento.model.Empresa;

@SuppressWarnings("serial")
@RequestScoped
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
