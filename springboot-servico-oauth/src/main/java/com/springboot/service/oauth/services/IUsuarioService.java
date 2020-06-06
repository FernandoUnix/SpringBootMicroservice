package com.springboot.service.oauth.services;

import com.springboot.app.commons.usuarios.models.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
	
}
