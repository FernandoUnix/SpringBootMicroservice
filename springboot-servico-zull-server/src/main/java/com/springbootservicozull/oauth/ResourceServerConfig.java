package com.springbootservicozull.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/security/oauth/token").permitAll()
				.antMatchers(HttpMethod.GET, "/api/produtos/listar", "/api/items/listar", "/api/usuarios/usuarios")
				.permitAll()
				.antMatchers(HttpMethod.GET, "/api/produtos/listar/{id}",
						"/api/items/listar/{id}/quantidade/{quantidade}", "/api/usuarios/usuarios/{id}")
				.hasAnyRole("ADMIN", "USER")
				.antMatchers("/api/produtos/**", "/api/items/**", "/api/usuarios/**").hasRole("ADMIN")
				.anyRequest().authenticated();
//				.antMatchers(HttpMethod.POST, "/api/produtos/criar", "/api/items/criar", "/api/usuarios/usuarios").hasRole("ADMIN")
//				.antMatchers(HttpMethod.PUT, "/api/produtos/editar/{id}", "/api/items/editar/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN")
//				.antMatchers(HttpMethod.DELETE, "/api/produtos/eliminar/{id}", "/api/items/eliminar/{id}", "/api/usuarios/usuarios/{id}").hasRole("ADMIN");
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter JwtAccessTokenConverter = new JwtAccessTokenConverter();
		JwtAccessTokenConverter.setSigningKey(jwtKey);
		return JwtAccessTokenConverter;
	}

}
