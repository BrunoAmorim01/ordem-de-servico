package br.com.atendimento.DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import com.uaihebert.uaicriteria.UaiCriteria;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;

import br.com.atendimento.model.Bairro;
import br.com.atendimento.model.EnderecoOS;

@SuppressWarnings("serial")
public class EnderecoDAO extends GenericDAO<EnderecoOS> implements Serializable {

	public boolean enderecoExiste(String logradouro) {

		List<EnderecoOS> resultado = porLogradouro(logradouro);

		return resultado.isEmpty() ? false : true;

	}

	public List<EnderecoOS> porLogradouro(String logradouro) {

		String consulta = "SELECT e FROM EnderecoOS e WHERE LOWER (e.logradouro) LIKE :logradouro";

		TypedQuery<EnderecoOS> query = manager.createQuery(consulta, EnderecoOS.class);
		query.setParameter("logradouro", "%" + logradouro + "%");
		List<EnderecoOS> resultado = query.getResultList();

		return resultado;

	}

	public List<EnderecoOS> porLogradouro(EnderecoOS enderecoOS) {
		String consulta = "SELECT e FROM EnderecoOS e WHERE LOWER (e.logradouro) LIKE :logradouro";

		TypedQuery<EnderecoOS> query = manager.createQuery(consulta, EnderecoOS.class);
		query.setParameter("logradouro", "%" + enderecoOS.getLogradouro() + "%");
		List<EnderecoOS> resultado = query.getResultList();

		return resultado;
	}

	public List<String> porLogradouroString(String logradouro) {

		String consulta = "SELECT e.logradouro FROM EnderecoOS e WHERE LOWER (e.logradouro) LIKE :logradouro";

		TypedQuery<String> query = manager.createQuery(consulta, String.class);
		query.setParameter("logradouro", "%" + logradouro + "%");
		List<String> resultado = query.getResultList();

		return resultado;

	}

	public List<EnderecoOS> filtrado(EnderecoOS enderecoOS) {
		UaiCriteria<EnderecoOS> uaiCriteria = UaiCriteriaFactory.createQueryCriteria(manager, EnderecoOS.class);		

		if (enderecoOS.getLogradouro() != null) {
			
			uaiCriteria.andStringLike(true, "logradouro", "%"+enderecoOS.getLogradouro()+"%");			
			
		}
		
		if (enderecoOS.getBairro().getCodigo() != null) {
			uaiCriteria.innerJoin("bairro");
			uaiCriteria.andEquals("bairro.codigo", enderecoOS.getBairro().getCodigo() );
			
		}		
		return uaiCriteria.getResultList();

	}

}
