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
		
				upload.save(file);
				System.out.println("CONTROL= "+UploadService.control + "  NOEXISTPROV= "+UploadService.noexistprov);
				if (UploadService.control==0 && UploadService.noexistprov==0)
					ms.addFlashAttribute("mensaje", "Archivo Cargado Exitosamente");
				else if (UploadService.control==1 && UploadService.noexistprov==0)
						ms.addFlashAttribute("mensaje", "Error: datos leídos inválidos");
				else if (UploadService.control==0 && UploadService.noexistprov==1)
					ms.addFlashAttribute("mensaje", "Algunos productos no se cargaron: Proveedor No Existe");	
				

		} catch (IOException e) {

			ms.addFlashAttribute("mensaje", "Error: no se seleccionó archivo para cargar");
			e.printStackTrace();
		}

		return "redirect:/csv";

	}

	//@PostMapping("/savepro")
	public String savepro(Producto productos) throws IOException {
			try {
				productoRepository.save(productos);
			} catch (Exception e) {
				UploadService.noexistprov=1; //Variable para mostrar mensaje , error por proveedor no existe
			}
			
			return "redirect:/csv";
	}

	@RequestMapping("/listarpro") //Metodo para listar Productos en DB
	public String verIndex(Model model) {
		System.out.println("llegó a listar productos");
		List<Producto> listaProductos = upload.listar();
		model.addAttribute("listaProductos", listaProductos);// Envia variable (nombre) y dato a la vista
		return "productosdb";
	}

	public boolean borrardb(Model model) { //Metodo para borrar la tabla productos before load los productos
		productoRepository.deleteAll();
		return false;
	}
	public void borrarall(Model model) { //Metodo para borrar la tabla productos before load los productos
		productoRepository.deleteAll();
		
	}

}
