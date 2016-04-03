package br.com.atendimento.Security;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

	@Inject
	ExternalContext externalContext;

	public String getNomeUsuario() {
		String nome = null;
		UsuarioSistema usuarioLogado = getUsuarioLogado();

		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNome();
		}

		return nome;
	}

	@Produces
	@UsuarioLogado
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) FacesContext
				.getCurrentInstance().getExternalContext().getUserPrincipal();

		if (token != null && token.getPrincipal() != null)
			usuario = (UsuarioSistema) token.getPrincipal();

		return usuario;
	}

	public boolean isSalvarOrdemServico() {
		return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("GERENTES")
				|| externalContext.isUserInRole("FUNCIONARIOS");
	}

	public boolean isCancelarOrdemServico() {
		return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("GERENTES")
				|| externalContext.isUserInRole("FUNCIONARIOS");
	}
	
	public boolean isFiscal() {
		return externalContext.isUserInRole("FISCAIS");
	}

}
