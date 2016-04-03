package br.com.atendimento.Service;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityTransaction;

import br.com.atendimento.DAO.ColaboradorDAO;
import br.com.atendimento.DAO.UsuarioDAO;
import br.com.atendimento.model.Colaborador;
import br.com.atendimento.model.Usuario;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
public class ColaboradorService implements Serializable {

	@Inject
	private ColaboradorDAO colaboradorDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Transactional
	public Colaborador salvar(Colaborador colaborador) {
		if (colaborador.getUsuario().getCodigo() == null) {
			Usuario usuario = usuarioDAO.merge(colaborador.getUsuario());
			colaborador.setUsuario(usuario);
		} else {
			usuarioDAO.merge(colaborador.getUsuario());
		}
		return colaboradorDAO.merge(colaborador);
	}

	@Transactional
	public void Excluir(Colaborador colaborador) {
		// colaboradorDAO.remove2(colaborador);
		colaboradorDAO.remove(colaborador);
		usuarioDAO.remove(colaborador.getUsuario());

	}

}
