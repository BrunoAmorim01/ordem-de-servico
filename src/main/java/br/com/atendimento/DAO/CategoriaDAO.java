package br.com.atendimento.DAO;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.com.atendimento.DAO.filter.CategoriaRankFilter;
import br.com.atendimento.model.Categoria;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.model.vo.CategoriaQuantidade;
import br.com.atendimento.util.jpa.Transactional;

@RequestScoped
public class CategoriaDAO extends GenericDAO<Categoria> {

	
	public List<Categoria> lista(String ordenacao) {
		System.out.println("lsit cat dao");

		String consulta = "SELECT c FROM Categoria c ORDER BY " + ordenacao;

		TypedQuery<Categoria> query = manager.createQuery(consulta, Categoria.class);

		List<Categoria> resultado = query.getResultList();		

		return resultado;
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaQuantidade> topCategorias(CategoriaRankFilter filter) {

		Session session = this.manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(OrdemServico.class);

		criteria.createAlias("categorias", "cat");

		if (filter.getDataDe() != null) {
			criteria.add(Restrictions.ge("dataOS", filter.getDataDe()));
		}

		if (filter.getDatafim() != null) {
			criteria.add(Restrictions.le("dataOS", filter.getDatafim()));
		}

		if (filter.getLimite() > 0) {
			criteria.setMaxResults(filter.getLimite());
		}

		ProjectionList projections = Projections.projectionList();
		projections.add(Projections.groupProperty("cat.nome").as("categoria"))
				.add(Projections.count("cat.codigo").as("quantidade"));

		criteria.setProjection(projections);
		criteria.addOrder(Order.desc("quantidade"));

		List<CategoriaQuantidade> resultado = criteria
				.setResultTransformer(Transformers.aliasToBean(CategoriaQuantidade.class)).list();

		int cont = resultado.size();

		while (cont > 0) {
			resultado.get(cont - 1).setRank(cont);
			cont--;
		}

		return resultado;

	}
}
