package br.com.atendimento.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.atendimento.DAO.UsuarioDAO;
import br.com.atendimento.model.Usuario;
import br.com.atendimento.util.CDIServiceLocator;

@Component
public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UsuarioDAO usuarios = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarios.porEmail(email);

		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		usuario.getGrupos().forEach(g -> authorities.add(new SimpleGrantedAuthority("ROLE_"+g.getNome().toUpperCase())));		
		return authorities;
	}

}
