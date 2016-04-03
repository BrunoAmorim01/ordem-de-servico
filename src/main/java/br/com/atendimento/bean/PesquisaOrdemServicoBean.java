package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Hibernate;

import br.com.atendimento.DAO.CategoriaDAO;
import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.DAO.filter.OrdemServicoFilter;
import br.com.atendimento.model.Categoria;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.model.StatusOrdemServico;

@Named
@ViewScoped
public class PesquisaOrdemServicoBean implements Serializable {

	private OrdemServicoFilter filter;

	private List<OrdemServico> ordemServicos;
	private List<Categoria> categorias;
	// private Categoria[] categorias;

	@Inject
	private OrdemServicoDAO ordemServicoDAO;

	@Inject
	private CategoriaDAO categoriaDAO;

	@PostConstruct
	public void init() {
		//categorias = categoriaDAO.list("nome");
		categorias = categoriaDAO.lista("nome");
		filter = new OrdemServicoFilter();
	}

	public void pesquisar() {

		ordemServicos = ordemServicoDAO.filtrados(filter);
		// ordemServicos = ordemServicoDAO.tudao();
	}

	public StatusOrdemServico[] getStatus() {
		return StatusOrdemServico.values();

	}

	public OrdemServicoFilter getFilter() {
		return filter;
	}

	public void setFilter(OrdemServicoFilter filter) {
		this.filter = filter;
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

//	 public Categoria[] getCategorias() {
//	 return categorias;
//	 }
//	
//	 public void setCategorias(Categoria[] categorias) {
//	 this.categorias = categorias;
//	 }

}
