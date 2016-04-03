package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.Security.UsuarioLogado;
import br.com.atendimento.Security.UsuarioSistema;
import br.com.atendimento.Service.RecebimentoOrdemServicoService;
import br.com.atendimento.bean.event.OsAlteradoEvent;
import br.com.atendimento.bean.event.OsEdicao;
import br.com.atendimento.model.OrdemServico;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class RecebimentoOrdemServicoBean implements Serializable {	
	
	@Inject
	@UsuarioLogado
	UsuarioSistema usuarioSistema;
	
	@Inject
	private RecebimentoOrdemServicoService recebimentoOsService;

	@Inject
	private OrdemServicoDAO ordemServicoDAO;	

	private List<OrdemServico> ordemServicos;

	private List<OrdemServico> ordemServicosSelecionados;

	@PostConstruct
	public void init() {
		ordemServicos = ordemServicoDAO.listaDesignados(usuarioSistema.getUsuario());
	}

	public void ReceberOrdemServico() {		
		recebimentoOsService.ReceberOrdemServico(ordemServicosSelecionados);
		init();
		
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public List<OrdemServico> getOrdemServicosSelecionados() {
		return ordemServicosSelecionados;
	}

	public void setOrdemServicosSelecionados(List<OrdemServico> ordemServicosSelecionados) {
		this.ordemServicosSelecionados = ordemServicosSelecionados;
	}

}
