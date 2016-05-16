package br.com.atendimento.DAO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.atendimento.DAO.filter.OrdemServicoFilter;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.model.StatusOrdemServico;
import br.com.atendimento.model.Usuario;

@SuppressWarnings("serial")
@RequestScoped
public class OrdemServicoDAO extends GenericDAO<OrdemServico> implements Serializable {

	@SuppressWarnings("unchecked")
	public List<OrdemServico> filtrados(OrdemServicoFilter filter) {

		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(OrdemServico.class);

		if (filter.getCodigo() != null) {

			criteria.add(Restrictions.eq("codigo", filter.getCodigo()));
		}

		if (filter.getProcesso() != null && !filter.getProcesso().isEmpty()) {
			System.out.println("processo");
			criteria.add(Restrictions.eq("processo", filter.getProcesso()));

		}

		if (filter.getDataDe() != null) {
			System.out.println("datade");
			criteria.add(Restrictions.ge("dataOS", filter.getDataDe()));

		}

		if (filter.getDatafim() != null) {
			System.out.println("datafim");
			criteria.add(Restrictions.le("dataOS", filter.getDatafim()));
		}

		if (filter.getCategorias() != null && filter.getCategorias().size() > 0) {

			criteria.createAlias("categorias", "cat");

			filter.getCategorias().forEach(c -> criteria.add(Restrictions.eq("cat.codigo", c.getCodigo())));

		}

		if (filter.getStatus() != null && filter.getStatus().length > 0) {

			criteria.add(Restrictions.in("statusOrdemServico", filter.getStatus()));
		}

		if (filter.getSolicitante() != null && !filter.getSolicitante().isEmpty()) {

			criteria.createAlias("contribuinte", "cont");
			criteria.add(Restrictions.ilike("cont.nome", filter.getSolicitante(), MatchMode.ANYWHERE));
		}

		if (filter.getFiscal() != null && !filter.getFiscal().isEmpty()) {

			criteria.createAlias("colaborador", "colab");
			criteria.createAlias("colab.usuario", "u1");
			criteria.add(Restrictions.ilike("u1.nome", filter.getFiscal(), MatchMode.ANYWHERE));
		}

		return criteria.list();

	}

	public List<OrdemServico> listaDesignados() {

		final String consulta = "SELECT DISTINCT os FROM OrdemServico os JOIN FETCH os.categorias  WHERE os.statusOrdemServico = :status";

		TypedQuery<OrdemServico> query = manager.createQuery(consulta, OrdemServico.class);

		query.setParameter("status", StatusOrdemServico.DESIGNADO);

		return query.getResultList();
	}

	public List<OrdemServico> listaDesignados(Usuario usuario) {

		final String consulta = "SELECT DISTINCT os FROM OrdemServico os JOIN FETCH os.categorias  WHERE os.statusOrdemServico = :status AND os.colaborador.usuario.codigo = :codigoUsuario";

		TypedQuery<OrdemServico> query = manager.createQuery(consulta, OrdemServico.class);

		query.setParameter("status", StatusOrdemServico.DESIGNADO);
		query.setParameter("codigoUsuario", usuario.getCodigo());

		return query.getResultList();
	}

	public void setToRecebido(OrdemServico ordemServico) {
		final String consulta = "update OrdemServico os set os.statusOrdemServico = :status where os.codigo = :codigo";
		Query query = manager.createQuery(consulta);
		query.setParameter("status", StatusOrdemServico.RECEBIDO);
		query.setParameter("codigo", ordemServico.getCodigo());
		query.executeUpdate();
	}

	public List<OrdemServico> listaRecebidos() {
		final String consulta = "SELECT DISTINCT os FROM OrdemServico os JOIN FETCH os.categorias  WHERE os.statusOrdemServico = :status";

		TypedQuery<OrdemServico> query = manager.createQuery(consulta, OrdemServico.class);

		query.setParameter("status", StatusOrdemServico.RECEBIDO);

		return query.getResultList();
	}
	
	public List<OrdemServico> listaRecebidos(Usuario usuario) {
		final String consulta = "SELECT DISTINCT os FROM OrdemServico os JOIN FETCH os.categorias  WHERE os.statusOrdemServico = :status AND os.colaborador.usuario.codigo = :codigoUsuario";

		TypedQuery<OrdemServico> query = manager.createQuery(consulta, OrdemServico.class);

		query.setParameter("status", StatusOrdemServico.RECEBIDO);
		query.setParameter("codigoUsuario", usuario.getCodigo());

		return query.getResultList();
	}

	public void setToConcluido(OrdemServico ordemServico) {
		final String consulta = "update OrdemServico os set os.statusOrdemServico = :status,os.dataBaixa = :dataBaixa where os.codigo = :codigo";
		Query query = manager.createQuery(consulta);

		query.setParameter("codigo", ordemServico.getCodigo());
		query.setParameter("dataBaixa", new Date());
		query.setParameter("status", StatusOrdemServico.CONCLUIDO);

		query.executeUpdate();
	}

}
