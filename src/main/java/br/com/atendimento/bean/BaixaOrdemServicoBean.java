package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.OrdemServicoDAO;
import br.com.atendimento.Security.UsuarioLogado;
import br.com.atendimento.Security.UsuarioSistema;
import br.com.atendimento.Service.BaixaOrdemServicoService;
import br.com.atendimento.model.OrdemServico;

@Named
@ViewScoped
public class BaixaOrdemServicoBean implements Serializable {
	@Inject
	@UsuarioLogado
	UsuarioSistema usuarioSistema;
	
	@Inject
	private OrdemServicoDAO ordemServicoDAO;

	@Inject
	private BaixaOrdemServicoService service;

	private List<OrdemServico> ordemServicos;
	private List<OrdemServico> ordemServicoselecionados;

	@PostConstruct
	public void init() {
		ordemServicos = ordemServicoDAO.listaRecebidos(usuarioSistema.getUsuario());
	}

	public void baixarOs() {
		service.baixarDeOrdemServico(ordemServicoselecionados);
		init();
		Messages.addGlobalInfo("As ordens de serviços estçao com status de 'Concluído'");
	}

	public List<OrdemServico> getOrdemServicos() {
		return ordemServicos;
	}

	public void setOrdemServicos(List<OrdemServico> ordemServicos) {
		this.ordemServicos = ordemServicos;
	}

	public List<OrdemServico> getOrdemServicoselecionados() {
		return ordemServicoselecionados;
	}

	public void setOrdemServicoselecionados(List<OrdemServico> ordemServicoselecionados) {
		this.ordemServicoselecionados = ordemServicoselecionados;
	}
}
