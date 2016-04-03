package br.com.atendimento.bean.relatorios;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.atendimento.DAO.CategoriaDAO;
import br.com.atendimento.DAO.filter.CategoriaRankFilter;
import br.com.atendimento.model.vo.CategoriaQuantidade;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class RankingCategoriasBean implements Serializable {

	@Inject
	private CategoriaDAO categoriaDAO;

	private CategoriaRankFilter filter;
	private List<CategoriaQuantidade> rankCategorias;

	@PostConstruct
	public void init() {
		filter = new CategoriaRankFilter();
	}

	public void pesquisarRanking() {
		rankCategorias = categoriaDAO.topCategorias(filter);
		
	}

	public List<CategoriaQuantidade> getRankCategorias() {
		return rankCategorias;
	}

	public void setRankCategorias(List<CategoriaQuantidade> rankCategorias) {
		this.rankCategorias = rankCategorias;
	}

	public CategoriaRankFilter getFilter() {
		return filter;
	}

	public void setFilter(CategoriaRankFilter filter) {
		this.filter = filter;
	}

}
