package br.com.atendimento.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Ignore;
import org.junit.Test;

import br.com.atendimento.model.Categoria;

public class CategoriaTest {

	@Test
	@Ignore
	public void salvar() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("AtendimentoOnline");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();

		Categoria categoria = new Categoria();
		categoria.setCodigo(3L);
		categoria.setNome("teste9");

		manager.merge(categoria);

		transaction.commit();
		factory.close();
		manager.close();
	}
}
