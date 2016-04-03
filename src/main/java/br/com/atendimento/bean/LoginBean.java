package br.com.atendimento.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omnifaces.util.Messages;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoginBean implements Serializable {

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletRequest request;

	@Inject
	private HttpServletResponse response;

	private String email;

	public void preRender(){
		if ("true".equals(request.getParameter("invalid"))) {
			Messages.addGlobalError("Usuário e/ou senha inválidos");
		}
	}
	
	public void login() throws ServletException, IOException{
		RequestDispatcher dispatcher=request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		facesContext.responseComplete();
	}
	
	public void logout() throws ServletException, IOException{
		RequestDispatcher dispatcher=request.getRequestDispatcher("/j_spring_security_logout");
		dispatcher.forward(request, response);
		facesContext.responseComplete();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
