package com.springboot.service.oauth.services;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springboot.app.commons.usuarios.models.Usuario;
import com.springboot.service.oauth.clientes.IUsuarioFeignClient;

import brave.Tracer;
import feign.FeignException;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioFeignClient usuarioFeignCliente;

	@Autowired
	private Tracer tracer;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		try {

			tracer.currentSpan().tag("nome.busca.usuario",username);
			Usuario usuario = usuarioFeignCliente.findByUsername(username);

			List<GrantedAuthority> authorities = usuario.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getNome()))
					.peek(role -> log.info("Role: " + role.getAuthority())).collect(Collectors.toList());

			log.info("Usuario autenticado: " + usuario.getNome());

			return new User(usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), true, true, true,
					authorities);

		} catch (FeignException e) {
			
			String error = "Erro ao efetuar login para o usuário: " + username + " | "+e.getMessage();
			
			tracer.currentSpan().tag("error.mensagem",error);
			log.error(error);
			throw new UsernameNotFoundException("Erro ao efetuar login para o usuário: " + username);
		}
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioFeignCliente.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return usuarioFeignCliente.update(usuario, id);
	}
}
