package br.com.atendimento.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.atendimento.DAO.ContribuinteDAO;
import br.com.atendimento.Service.ContribuinteService;
import br.com.atendimento.model.Contribuinte;
import br.com.atendimento.util.jpa.Transactional;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ContribuinteBean implements Serializable{

	@Inject
	ContribuinteDAO contribuinteDAO;

	@Inject
	ContribuinteService contribuinteService;

	private Contribuinte contribuinte;

	List<Contribuinte> contribuintes;

	@PostConstruct
	public void init() {
		contribuintes = contribuinteDAO.list();
	}

	public void novo() {
		contribuinte = new Contribuinte();
	}

	public void salvar() {
		contribuinteService.salvar(contribuinte);
		contribuintes = contribuinteDAO.list();

		Messages.addGlobalInfo("Contribuinte " + contribuinte.getNome() + " salvo com sucesso");
	}

	@Transactional
	public void excluir(ActionEvent event) {
		contribuinte = (Contribuinte) event.getComponent().getAttributes().get("contribuinteSelecionado");
		contribuinteDAO.remove(contribuinte);
		Messages.addGlobalInfo("Contribuinte " + contribuinte.getNome() + " excluido com sucesso");
		init();		
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
