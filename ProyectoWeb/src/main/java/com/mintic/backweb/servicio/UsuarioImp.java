package com.mintic.backweb.servicio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mintic.backweb.modelo.LoginDto;
import com.mintic.backweb.modelo.Usuario;
import com.mintic.backweb.repositorio.IUsuario;
/*
 * Implementamos la interface con sus metodos
 * @Service  indica la logina empresarial toda la logica de negocio */
@Service
public class UsuarioImp implements IUsuarioService {

	/*
	 * @Autowired inyeccion de dependencia  en este caso para acceder a los metodos del repositorio IUsuario
	 *  (accedemos al login)
	 * */
	@Autowired
	IUsuario iUsuario;
	/*
	 * Motodo para validar si existe el usuario
	 */

	@Override
	public int login(LoginDto usuarioDto) {
		int u = iUsuario.findByNombreUsuarioAndPassword(usuarioDto.getNombreUsuario(), usuarioDto.getPassword());
		return u;
	}

	@Override
	public ResponseEntity<?> ingresar(LoginDto usuarioDto) {
		Map<String, Object> response = new HashMap<>();
		Usuario usuario = null;	
		try {
			usuario = iUsuario.findByNameAndPassword(usuarioDto.getNombreUsuario(), usuarioDto.getPassword());
			 if (usuario ==null) {
				 response.put("Usuario", null);
				 response.put("Mensaje", "Alerta:Usuario o Password incorrectos");
				 response.put("statusCode", HttpStatus.NOT_FOUND.value());
				 return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		 	
			 }else {
				 response.put("Usuario", usuario);
				 response.put("Mensaje", "Datos correctos");
				 response.put("statusCode", HttpStatus.OK.value());
				 return new ResponseEntity<>(response, HttpStatus.OK);
			 }
		} catch (Exception e) {
			 response.put("Usuario", null);
			 response.put("Mensaje", "Ha ocurrido un error");
			 response.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
			 return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
