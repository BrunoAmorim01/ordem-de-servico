package br.com.atendimento.Service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.atendimento.DAO.CategoriaDAO;
import br.com.atendimento.model.Categoria;
import br.com.atendimento.util.jpa.Transactional;

public class CategoriaService implements Serializable {

	private static final long serialVersionUID = 7672553057650654684L;

	@Inject
	CategoriaDAO categoriaDAO;

	@Transactional
	public Categoria salvar(Categoria categoria) {
		Categoria categoria2 = categoriaDAO.merge(categoria);
		return categoria2;
	}

	@Transactional
	public void delete(Categoria categoria) {
		Categoria categoria2 = categoriaDAO.porID(categoria.getCodigo());
		categoriaDAO.remove(categoria2);
	}

	public List<Categoria> listar() {
		return categoriaDAO.list();
	}
}
