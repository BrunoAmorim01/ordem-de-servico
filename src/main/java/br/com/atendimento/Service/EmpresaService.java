package br.com.atendimento.Service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.atendimento.DAO.EmpresaDAO;
import br.com.atendimento.model.Empresa;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
public class EmpresaService implements Serializable {

	@Inject
	EmpresaDAO empresaDAO;

	@Transactional
	public Empresa salvar(Empresa empresa) {
		return empresaDAO.merge(empresa);
	}

	@Transactional
	public void excluir(Empresa empresa) {
		empresaDAO.remove(empresa);
	}

}
