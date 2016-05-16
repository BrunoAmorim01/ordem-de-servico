package br.com.atendimento.util.jpa;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;

@Interceptor
@Transactional
public class TransactionInterceptor {

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