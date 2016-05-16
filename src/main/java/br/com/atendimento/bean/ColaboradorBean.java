package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.ColaboradorDAO;
import br.com.atendimento.DAO.EmpresaDAO;
import br.com.atendimento.Service.ColaboradorService;
import br.com.atendimento.bean.event.ColaboradorAlteradoEvent;
import br.com.atendimento.model.Colaborador;
import br.com.atendimento.model.Empresa;
import br.com.atendimento.model.Grupo;
import br.com.atendimento.model.Usuario;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ColaboradorBean implements Serializable {
	

	@Inject
	private ColaboradorService colaboradorService;

	@Inject
	private ColaboradorDAO colaboradorDAO;

	@Inject
	private EmpresaDAO empresaDAO;

	private Colaborador colaborador;
	private Usuario usuario;
	private List<Colaborador> colaboradores;
	private List<Empresa> empresas;

	private Empresa empresa;

	@Inject
	private List<Grupo> grupos;

//	@Inject
//	private Event<ColaboradorAlteradoEvent> event;

	@PostConstruct
	public void init() {
		colaboradores = colaboradorDAO.list();
	}

	public void novo() {
		colaborador = new Colaborador();
		usuario = new Usuario();
		colaborador.setUsuario(new Usuario());
		empresas = empresaDAO.list();
	}

	public void ColaboradorAlterado(@Observes ColaboradorAlteradoEvent event) {
		colaborador = event.getColaborador();
	}

	public void salvar() {

		colaboradorService.salvar(colaborador);

		colaboradores = colaboradorDAO.list();
		Messages.addGlobalInfo("Colaborador " + colaborador.getUsuario().getNome() + " salvo com sucesso");
		novo();

	}

	public void carregarColaborador(ActionEvent event) {
		colaborador = (Colaborador) event.getComponent().getAttributes().get("colaboradorSelecionado");
		empresas = empresaDAO.list();
		colaborador = colaboradorDAO.porID(colaborador.getCodigo());

	}

	public void excluir(ActionEvent event) {
		colaborador = (Colaborador) event.getComponent().getAttributes().get("colaboradorSelecionado");

		colaboradorService.Excluir(colaborador);
		Messages.addGlobalInfo("Colaborador " + colaborador.getUsuario().getNome() + " excluído com sucesso");
		colaboradores = colaboradorDAO.list();
	}

	public void colaboradorAlterado(@Observes ColaboradorAlteradoEvent event) {
		colaborador = event.getColaborador();
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}
