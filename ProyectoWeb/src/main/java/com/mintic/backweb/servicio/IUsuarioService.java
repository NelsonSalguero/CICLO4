package com.mintic.backweb.servicio;

import org.springframework.http.ResponseEntity;

import com.mintic.backweb.modelo.LoginDto;

public interface IUsuarioService {
	
	int login(LoginDto usuarioDto);

	ResponseEntity<?> ingresar(LoginDto usuarioDto);
	
}
