package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Hibernate;
import org.omnifaces.util.Messages;
import org.primefaces.event.SelectEvent;

import br.com.atendimento.DAO.BairroDAO;
import br.com.atendimento.DAO.CategoriaDAO;
import br.com.atendimento.DAO.ColaboradorDAO;
import br.com.atendimento.DAO.EnderecoDAO;
import br.com.atendimento.DAO.FuncionarioDAO;
import br.com.atendimento.Service.OrdemServicoService;
import br.com.atendimento.bean.event.OsAlteradoEvent;
import br.com.atendimento.bean.event.OsEdicao;
import br.com.atendimento.model.Bairro;
import br.com.atendimento.model.Categoria;
import br.com.atendimento.model.Colaborador;
import br.com.atendimento.model.Contribuinte;
import br.com.atendimento.model.Empresa;
import br.com.atendimento.model.EnderecoOS;
import br.com.atendimento.model.Funcionario;
import br.com.atendimento.model.OrdemServico;
import br.com.atendimento.model.OrdemServicoEndereco;
import br.com.atendimento.model.StatusOrdemServico;
import br.com.atendimento.util.jpa.Transactional;
import br.com.atendimento.util.jsf.FacesUtil;

@SuppressWarnings("serial")
@Named("osCadastroBean")
@ViewScoped
public class CadastroOrdemServicoBean implements Serializable {

	@Produces
	@OsEdicao
	private OrdemServico ordemServico;
	private EnderecoOS enderecoOS;
	private String referencia;

	private OrdemServicoEndereco ordemServicoEndereco;
	private Empresa empresa;

	private List<Bairro> bairros;
	private List<Categoria> categorias;
	private List<Colaborador> colaboradores;
	private List<EnderecoOS> enderecos;
	private List<Funcionario> funcionarios;
	@Inject
	private List<Empresa> empresas;

	@Inject
	private OrdemServicoService service;
	@Inject
	private CategoriaDAO categoriaDAO;
	@Inject
	private ColaboradorDAO colaboradorDAO;
	@Inject
	private EnderecoDAO enderecoDAO;
	@Inject
	private BairroDAO bairroDAO;
	@Inject
	private FuncionarioDAO funcionarioDAO;

	public CadastroOrdemServicoBean() {
		novo();
	}

	// @PostConstruct
	public void init() {

		if (FacesUtil.isNotPostback()) {
			categorias = categoriaDAO.list();
			bairros = bairroDAO.list();
			funcionarios = funcionarioDAO.list();

			try {
				empresa = ordemServico.getColaborador().getEmpresa();
				carregarFiscais();

			} catch (NullPointerException exception) {

			}
		}
	}

	public void novo() {
		ordemServico = new OrdemServico();
		ordemServico.setDataOS(new Date());
		ordemServico.setStatusOrdemServico(StatusOrdemServico.ABERTO);
		ordemServico.setContribuinte(new Contribuinte());

		ordemServicoEndereco = new OrdemServicoEndereco();
		ordemServicoEndereco.setEndereco(new EnderecoOS());
		enderecoOS = new EnderecoOS();
	}

	public void salvar() {
		try {
			ordemServico = service.save(ordemServico);
		} catch (Exception exception) {
			Messages.addGlobalError("Ocorreu um erro");
			exception.printStackTrace();
		}

		Messages.addGlobalInfo("Ordem de serviço salva no sistema");
	}

	public void setarContribuinte(SelectEvent event) {
		Contribuinte contribuinte = (Contribuinte) event.getObject();
		ordemServico.setContribuinte(contribuinte);
	}

	public void carregarFiscais() {
		try {
			colaboradores = colaboradorDAO.listColaboradorPorempresa(empresa);
		} catch (NullPointerException exception) {
			colaboradores = new ArrayList<>();
		}
	}

	public List<EnderecoOS> carregarEnderecos(String logradouro) {

		FacesContext context = FacesContext.getCurrentInstance();
		enderecoOS = (EnderecoOS) UIComponent.getCurrentComponent(context).getAttributes().get("logPesquisa");
		enderecoOS.setLogradouro(logradouro);

		return enderecoDAO.filtrado(enderecoOS);
	}

	@Transactional
	public void adicionarEndereco() {
		enderecos = enderecoDAO.porLogradouro(enderecoOS.getLogradouro());
		System.out.println("inocio" + enderecos.get(0).getCodigo());
		OrdemServicoEndereco endereco = new OrdemServicoEndereco();

		if (enderecos.isEmpty()) {
			endereco.setEndereco(enderecoDAO.merge(enderecoOS));
		} else {
			endereco.setEndereco(enderecos.get(0));
		}

		endereco.setOrdemServico(ordemServico);
		endereco.setReferencia(referencia);
		ordemServico.getServicoEndereco().add(endereco);

		enderecoOS = new EnderecoOS();
		referencia = null;
	}

	public void removeEndereco(ActionEvent event) {

		ordemServicoEndereco = (OrdemServicoEndereco) event.getComponent().getAttributes().get("enderecoSelecionado");
		ordemServico.getServicoEndereco().removeIf(se -> se.getEndereco() == ordemServicoEndereco.getEndereco());
	}

	public void OrdemServicoAlterada(@Observes OsAlteradoEvent event) {
		ordemServico = event.getOrdemServico();
	}

	public boolean isEditando() {
		try {
			return this.ordemServico.getCodigo() != null;
		} catch (NullPointerException exception) {
			return false;
		}

	}

	public OrdemServico getOrdemServico() {
		if (ordemServico == null) {
			novo();
		}
		return ordemServico;
	}

	public void setOrdemServico(OrdemServico ordemServico) {
		this.ordemServico = ordemServico;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Bairro> getBairros() {
		return bairros;
	}

	public void setBairros(List<Bairro> bairros) {
		this.bairros = bairros;
	}

	public OrdemServicoEndereco getOrdemServicoEndereco() {
		return ordemServicoEndereco;
	}

	public void setOrdemServicoEndereco(OrdemServicoEndereco ordemServicoEndereco) {
		this.ordemServicoEndereco = ordemServicoEndereco;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<EnderecoOS> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<EnderecoOS> enderecos) {
		this.enderecos = enderecos;
	}

	public EnderecoOS getEnderecoOS() {
		return enderecoOS;
	}

	public void setEnderecoOS(EnderecoOS enderecoOS) {
		this.enderecoOS = enderecoOS;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

}
