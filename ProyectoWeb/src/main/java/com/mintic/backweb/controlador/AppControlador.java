package com.mintic.backweb.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.mintic.backweb.modelo.LoginDto;
import com.mintic.backweb.repositorio.ITipoDocumento;
import com.mintic.backweb.servicio.IUsuarioService;

@RestController
public class AppControlador {
	
	/*
	 * inyectamos el la iterface del servicio para acceder a los metodos del negocio
	 **/
	@Autowired
	IUsuarioService iUsuario;
	
	@Autowired
	ITipoDocumento iTipoDocumento;

	/*
	 * @CrossOrigin indica desde que sitios se van a realizar peticiones
	 */
	@CrossOrigin(origins = { "http://localhost:8010" })
	@PostMapping("/loginclient") // ruta del servicio desde el front deben direccionar a esta ruta
	public int login(@RequestBody LoginDto usuario) {
		int responseLogin = iUsuario.login(usuario);
		return responseLogin;
	}

	@PostMapping("/login") // ruta del servicio desde el front deben direccionar a esta ruta
	public ResponseEntity<?> loginCliente(@RequestBody LoginDto usuario) {
		return iUsuario.ingresar(usuario);
	}
	
	
}
