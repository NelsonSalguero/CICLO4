package com.mintic.tiendafront.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.mintic.tiendafront.cliente.IClienteTienda;
import com.mintic.tiendafront.modelo.LoginDto;

@Controller
public class AppControlador {

	@Autowired
	IClienteTienda clienteTienda;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}

	@PostMapping("/login")
	public String login(Model model, LoginDto loginDto) {
		int validLogin = clienteTienda.login(loginDto);

		if (validLogin == 1) {
			return "menu";
		} else {
			model.addAttribute("error", "Usuario o contraseña errados, intente de nuevo.");
			return "index";
		}

	}
	
	
	
}
