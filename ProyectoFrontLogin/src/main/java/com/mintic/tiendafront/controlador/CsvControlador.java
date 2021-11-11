package com.mintic.tiendafront.controlador;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mintic.tiendafront.modelo.Producto;
import com.mintic.tiendafront.repository.ProductoRepository;
import com.mintic.tiendafront.service.UploadService;

@Controller
public class CsvControlador {
	@Autowired
	private UploadService upload;
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired

	@GetMapping("/csv")
	public String homecsv() {
		return "productos";
	}

	@PostMapping("/cargarcsv")
	public String carga(@RequestParam("archivo") MultipartFile file, RedirectAttributes ms) {
		try {
			if (file != null || file.getSize() > 0 || !file.isEmpty()) {
				upload.save(file);
				ms.addFlashAttribute("mensaje", "Archivo Guardado y Cargado a la Base de Datos Correctamente");
			}
			

		} catch (IOException e) {

			ms.addFlashAttribute("mensaje", "No se seleccionó ningun archivo o es Incorrecto");
			e.printStackTrace();
		}

		return "redirect:/csv";

	}

	@PostMapping("/savepro")
	public String savepro(Producto productos) throws IOException {
		try {
			productoRepository.save(productos);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Que error saldría");
		}
		return "redirect:/csv";
	}

	@RequestMapping("/listarpro")
	public String verIndex(Model model) {
		System.out.println("llegó a listar productos");
		List<Producto> listaProductos = upload.listar();
		model.addAttribute("listaProductos", listaProductos);// Envia variable (nombre) y dato a la vista
		return "productosdb";
	}

}
