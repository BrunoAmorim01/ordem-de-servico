package br.com.atendimento.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/home.xhtml").access("isAuthenticated()")
				.antMatchers("/empresa/**", "/categoria/**", "/funcionario/**", "/contribuinte/**").access("hasRole('ADMINISTRADORES')")
				.antMatchers("/relatorios/**", "/ordemServico/**").hasAnyRole("FISCAIS", "FUNCIONARIOS","ADMINISTRADORES")

				.and().formLogin().loginPage("/login.xhtml").usernameParameter("email").passwordParameter("senha")
				.failureUrl("/login.xhtml?invalid=true").defaultSuccessUrl("/home.xhtml")
				.and().exceptionHandling().accessDeniedPage("/acessonegado.xhtml");

	}

	@Autowired
	private UserDetailsService user;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(user);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

}
