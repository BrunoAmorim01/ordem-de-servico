package br.com.atendimento.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.DAO.filter.OrdemServicoFilter;
import br.com.atendimento.model.Categoria;

@Named
@ViewScoped
public class TabBean implements Serializable {

	private Categoria categoria;
	
	@Inject
	private OrdemServicoDAO dao;

	@PostConstruct
	public void init() {
		categoria = new Categoria();
		categoria.setCodigo(99L);;
		categoria.setNome("hhh");
		//dao.topCategorias(new OrdemServicoFilter());
		
	}

	public void mostrar() {
		System.out.println(categoria.getNome());
		
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
