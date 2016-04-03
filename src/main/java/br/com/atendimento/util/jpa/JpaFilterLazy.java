package br.com.atendimento.util.jpa;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class JpaFilterLazy implements Filter {

	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("AtendimentoOnline");

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		EntityManager manager = factory.createEntityManager();

		try {
			manager.getTransaction().begin();
			// realiza as ações do sistema
			filterChain.doFilter(request, response);
			manager.getTransaction().commit();
		} catch (Exception ex) {
			if (manager != null && manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
		} finally {
			if (manager != null && manager.isOpen()) {
				manager.close();
			}
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
