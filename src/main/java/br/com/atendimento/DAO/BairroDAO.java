package br.com.atendimento.DAO;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import br.com.atendimento.model.Bairro;

@SuppressWarnings("serial")
@RequestScoped
public class BairroDAO extends GenericDAO<Bairro> implements Serializable {

}
