package br.com.atendimento.util.jpa;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject EntityManager manager;

	@AroundInvoke
	public Object gerenciaTransacao(InvocationContext context) throws Exception {

		EntityTransaction tx = null;
		try {
			// em ambiente JTA apenas o getTransaction já lança exceção
			tx = manager.getTransaction();
			tx.begin();
			Object retorno = context.proceed();
			tx.commit();
			return retorno;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

	}

}