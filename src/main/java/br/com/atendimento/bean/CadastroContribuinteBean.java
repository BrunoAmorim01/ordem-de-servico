package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.ContribuinteDAO;
import br.com.atendimento.Service.ContribuinteService;
import br.com.atendimento.model.Contribuinte;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class CadastroContribuinteBean implements Serializable {

	private Contribuinte contribuinte;
	private List<Contribuinte> contribuintes;

	@Inject
	private ContribuinteDAO contribuinteDAO;

	@Inject
	private ContribuinteService contribuinteService;

	@PostConstruct
	public void init() {
		contribuinte = new Contribuinte();
		contribuintes=new ArrayList<>();
	}

	public void addContribuinte() {
		contribuinte = contribuinteService.salvar(contribuinte);
		Messages.addGlobalFatal("Contribuinte salvo Com sucesso");
	}

	public List<Contribuinte> carregarContribuintes(String nome) {
		if(!nome.isEmpty()){
			contribuintes=contribuinteDAO.porNome(nome);	
		}		
		
		return contribuintes;

	}

	public Contribuinte getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}

	public List<Contribuinte> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
	}
}
