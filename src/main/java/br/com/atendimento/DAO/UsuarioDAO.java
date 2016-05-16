package br.com.atendimento.DAO;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.omnifaces.util.Messages;

import br.com.atendimento.model.Usuario;

@SuppressWarnings("serial")
@RequestScoped
public class UsuarioDAO extends GenericDAO<Usuario> implements Serializable {

	@Inject
	EntityManager manager;

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		final String consulta = "from Usuario where lower(email) = :email";

		try {
			TypedQuery<Usuario> query = manager.createQuery(consulta, Usuario.class);
			query.setParameter("email", email.toLowerCase());
			usuario = query.getSingleResult();
		} catch (NoResultException exception) {
			Messages.addGlobalError("Nenhum usuario encontrado com o e-mail informado");
		}

		return usuario;
	}

}
