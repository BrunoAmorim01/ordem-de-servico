package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.CategoriaDAO;
import br.com.atendimento.DAO.filter.CategoriaRankFilter;
import br.com.atendimento.Service.CategoriaService;
import br.com.atendimento.model.Categoria;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CategoriaBean implements Serializable {	

	@Inject	
	private CategoriaDAO categoriaDAO;

	@Inject
	private CategoriaService categoriaService;

	private Categoria categoria;
	private CategoriaRankFilter filter;
	private List<Categoria> categorias;

	@PostConstruct
	public void init() {	
		
		System.out.println("init cat");
		
		categoria = new Categoria();
		categorias = categoriaService.listar();
	}

	public void salvar() {

		//categoria = categoriaService.salvar(categoria);
		categorias = categoriaDAO.list("nome");
		init();
		Messages.addGlobalInfo("Categoria salva com sucesso");

	}

	@Transactional
	public void excluir(ActionEvent evento) {
		categoria = (Categoria) evento.getComponent().getAttributes().get("catSelecionada");
		// categoriaService.delete(categoria);
		categoriaDAO.remove(categoria);
		categorias = categoriaDAO.list("nome");
		Messages.addGlobalInfo("Categoria excluida com sucesso");
	}	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public CategoriaRankFilter getFilter() {
		return filter;
	}

	public void setFilter(CategoriaRankFilter filter) {
		this.filter = filter;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
