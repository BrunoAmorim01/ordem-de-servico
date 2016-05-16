package br.com.atendimento.Service;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.atendimento.DAO.FuncionarioDAO;
import br.com.atendimento.DAO.UsuarioDAO;
import br.com.atendimento.model.Funcionario;
import br.com.atendimento.model.Usuario;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
@RequestScoped
public class FuncionarioService implements Serializable {

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Transactional
	public Funcionario salvar(Funcionario funcionario) {
		if (funcionario.getUsuario().getCodigo() == null) {
			Usuario usuario = usuarioDAO.merge(funcionario.getUsuario());
			funcionario.setUsuario(usuario);
		} else {
			usuarioDAO.merge(funcionario.getUsuario());
		}

		return funcionarioDAO.merge(funcionario);
	}

	@Transactional
	public void remove(Funcionario funcionario) {
		funcionarioDAO.remove(funcionario);
		usuarioDAO.remove(funcionario.getUsuario());

	}
}
