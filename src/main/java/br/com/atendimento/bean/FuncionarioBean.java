package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.FuncionarioDAO;
import br.com.atendimento.Service.FuncionarioService;
import br.com.atendimento.model.Funcionario;
import br.com.atendimento.model.Grupo;
import br.com.atendimento.model.Usuario;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class FuncionarioBean implements Serializable {

	@Inject
	private FuncionarioDAO funcionarioDAO;

	@Inject
	private FuncionarioService funcionarioService;

	@Inject
	private List<Grupo> grupos;

	private List<Funcionario> funcionarios;

	private Funcionario funcionario;

	@PostConstruct
	public void init() {
		funcionarios = funcionarioDAO.list();
	}

	public void novo() {
		funcionario = new Funcionario();
		funcionario.setUsuario(new Usuario());
	}

	public void carregarFuncionario(ActionEvent event) {
		funcionario = (Funcionario) event.getComponent().getAttributes().get("funcionarioSelecionado");
		funcionario = funcionarioDAO.porID(funcionario.getCodigo());
	}

	public void salvar() {
		funcionarioService.salvar(funcionario);
		Messages.addGlobalInfo("Funcionario " + funcionario.getUsuario().getNome() + " salvo com sucesso");
		novo();
		init();
		funcionarios = funcionarioDAO.list();

	}

	public void excluir(ActionEvent event) {
		funcionario = (Funcionario) event.getComponent().getAttributes().get("funcionarioSelecionado");
		funcionarioService.remove(funcionario);
		funcionarios = funcionarioDAO.list();
		Messages.addGlobalInfo("Funcionario " + funcionario.getUsuario().getNome() + " excluido com sucesso");
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
