package com.springboot.service.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import com.springboot.app.commons.usuarios.models.Usuario;
import com.springboot.service.oauth.services.IUsuarioService;

import brave.Tracer;
import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private Tracer tracer;

	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {

		log.info("Sucesso ao efetuar login: " + authentication.getName());

		if(!authentication.getName().equals("frontendapp")){
	
			try {
	
				Usuario usuario = usuarioService.findByUsername(authentication.getName());
	
				if (usuario.getTentativas() != null && usuario.getTentativas() > 0) {
					usuario.setTentativas(0);
				}
	
				usuarioService.update(usuario, usuario.getId());
	
			} catch (FeignException e) {
				log.error("usuario não existe no sistema " + authentication.getName());
				log.error(e.getMessage());
			}
		}
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {

		String msgErrorLogin = "Erro ao efetuar login: " + authentication.getName() + " | " + exception.getMessage();
		log.error(msgErrorLogin);

		try {

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(msgErrorLogin);

			log.info("falha ao executar login para o usuario " + authentication.getName());

			Usuario usuario = usuarioService.findByUsername(authentication.getName());

			if (usuario.getTentativas() == null) {
				usuario.setTentativas(0);
			}

			usuario.setTentativas(usuario.getTentativas() + 1);

			if (usuario.getTentativas() >= 3) {

				String desabilitado = "| usuario: " + authentication.getName()
						+ " desabilitado por maximo de tentativas";
				stringBuilder.append(desabilitado);
				log.info(desabilitado);

				usuario.setEnabled(false);
			}

			tracer.currentSpan().tag("error.mensagem", stringBuilder.toString());

			usuarioService.update(usuario, usuario.getId());

		} catch (FeignException e) {

			log.error("usuario não existe no sistema " + authentication.getName());
			log.error(e.getMessage());
		}
	}
}
