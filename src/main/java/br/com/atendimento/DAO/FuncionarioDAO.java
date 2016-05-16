package br.com.atendimento.DAO;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import br.com.atendimento.model.Funcionario;

@SuppressWarnings("serial")
@RequestScoped
public class FuncionarioDAO extends GenericDAO<Funcionario> implements Serializable {

}
