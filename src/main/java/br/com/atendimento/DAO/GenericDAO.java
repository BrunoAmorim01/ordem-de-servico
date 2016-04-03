package br.com.atendimento.DAO;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import br.com.atendimento.util.jpa.Transactional;

public class GenericDAO<T> implements Dao<T> {
	private static final Logger LOGGER;
	private final Class<T> classeDeEntidade;
	private final Field campoId;

	@Inject
	protected EntityManager manager;

	static {
		LOGGER = Logger.getLogger(GenericDAO.class.getName());
	}

	public GenericDAO() {
		this.classeDeEntidade = this.buscarClasseDeEntidade();
		this.campoId = this.buscarCampoId();
	}

	@Override
	public T merge(T t) {
		return manager.merge(t);
	}

	@Override	
	public void remove(T t) {		
		Object id = this.getValorDoId(t);
		T entidadeGerenciada = porIdLazy(id);
		manager.remove(entidadeGerenciada);
		manager.flush();

	}

	@Override
	public List<T> list() {
		String nomeDeEntidade = this.classeDeEntidade.getSimpleName();
		String jpql = "SELECT t FROM " + nomeDeEntidade + " t";

		return manager.createQuery(jpql, this.classeDeEntidade).getResultList();
	}

	public List<T> list(String ordenacao) {
		String nomeDeEntidade = this.classeDeEntidade.getSimpleName();
		String jpql = "SELECT t FROM " + nomeDeEntidade + " t " + " ORDER BY " + "t." + ordenacao;

		return manager.createQuery(jpql, this.classeDeEntidade).getResultList();
	}

	@Override
	public T porID(Long codigo) {
		return manager.find(classeDeEntidade, codigo);
	}

	public T porID(final Object id) {
		return manager.find(classeDeEntidade, id);
	}

	public T porIdLazy(final Object id) {
		return manager.getReference(this.classeDeEntidade, id);
	}

	@SuppressWarnings("unchecked")
	private Class<T> buscarClasseDeEntidade() {
		Class<?> classeDAO = this.getClass();

		while (classeDAO.getSuperclass() != GenericDAO.class) {
			classeDAO = classeDAO.getSuperclass();
		}

		ParameterizedType tipo = (ParameterizedType) classeDAO.getGenericSuperclass();

		Class<T> classeDeEntidadeBuscada = null;

		try {
			classeDeEntidadeBuscada = (Class<T>) tipo.getActualTypeArguments()[0];
		} catch (ClassCastException exception) {
			String message = "não é possível moldar : " + tipo.getActualTypeArguments()[0];
			LOGGER.log(Level.SEVERE, message, exception);
			throw exception;
		}
		return classeDeEntidadeBuscada;
	}

	protected Object getValorDoId(T entidade) {
		Object id = null;
		boolean acessibilidadeAnterior = this.campoId.isAccessible();
		this.campoId.setAccessible(true);

		try {
			id = this.campoId.get(entidade);
		} catch (IllegalAccessException ex) {
			String msg = "não é possível buscar o valor do campo id: " + this.campoId;
			LOGGER.log(Level.SEVERE, msg, ex);
			throw new RuntimeException(msg, ex);
		}

		this.campoId.setAccessible(acessibilidadeAnterior);

		return id;
	}

	private Field buscarCampoId() {
		Field campoId = null;
		Class<?> classeDeEntidade = this.classeDeEntidade;

		pesquisaCampoId: while (campoId == null) {

			for (Field field : classeDeEntidade.getDeclaredFields()) {

				if (field.getAnnotation(Id.class) != null) {
					campoId = field;
					break pesquisaCampoId;
				}
			}

			classeDeEntidade = classeDeEntidade.getSuperclass();
		}

		return campoId;
	}
}
