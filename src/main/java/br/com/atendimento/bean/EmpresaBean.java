package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.EmpresaDAO;
import br.com.atendimento.Service.EmpresaService;
import br.com.atendimento.model.Empresa;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {

	private static final long serialVersionUID = -2776060745946069975L;

	@Inject
	private EmpresaDAO empresaDAO;

	@Inject
	private EmpresaService service;

	private Empresa empresa;
	private List<Empresa> empresas;

	@PostConstruct
	public void init() {
		empresa = new Empresa();
		empresas = empresaDAO.list();
	}

	public void salvar() {
		empresa = service.salvar(empresa);
		Messages.addGlobalInfo("Empresa cadastrada com sucesso");
		init();
	}

	public void excluir(ActionEvent event) {
		empresa = (Empresa) event.getComponent().getAttributes().get("empresaSelecionada");
		service.excluir(empresa);
		init();
		Messages.addGlobalInfo("Empresa excluida com sucesso");

	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
