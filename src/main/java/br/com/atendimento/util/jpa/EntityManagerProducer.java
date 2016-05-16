package br.com.atendimento.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	EntityManagerFactory factory;
	
	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("AtendimentoOnline");
	}

	@Produces
	@RequestScoped
	public EntityManager create() {
		System.out.println("entity aberto");
		return factory.createEntityManager();
	}

	public void close(@Disposes EntityManager manager) {
		System.out.println("entity fechado");
		manager.close();
	}

}
