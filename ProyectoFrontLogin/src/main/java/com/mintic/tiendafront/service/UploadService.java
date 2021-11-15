package com.mintic.tiendafront.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;



//import org.slf4j.Logger; (estoy usando otra forma de mandar avisos a consola)
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.mintic.tiendafront.controlador.CsvControlador;
import com.mintic.tiendafront.modelo.Producto;
import com.mintic.tiendafront.repository.ProductoRepository;
import com.mintic.tiendafront.service.UploadService;

@Service
//@Transactional ( si se pone esta linea, se revienta cuando no encuentra proveedor válido)
public class UploadService {

	public static int control=0; //Variables para seleccionar el tipo d emensaje que mostrará segun error
	public static int noexistprov=0;
	@Autowired
	CsvControlador saveBD = new CsvControlador();
		
	//private final Logger logg = LoggerFactory.getLogger(UploadService.class); //Estoy usando System.out.println, no se requiere
	public List<Producto> save(MultipartFile file) throws IOException {
		saveBD.borrardb(null);//Borrar la tabla productos en DB antes de cargar productos
		control=0;noexistprov=0; //Inicializar variables para mensajes en la vista
		byte[] bytes = file.getBytes(); 	 	
		Path pathy = Paths.get(file.getOriginalFilename());
		Files.write(pathy, bytes);
		BufferedReader BR = new BufferedReader(new FileReader(file.getOriginalFilename()));
		String linea = BR.readLine();
		List<Producto> productos = new ArrayList<>();
		while (linea != null) {
			
			String[] campos = linea.split(String.valueOf(","));
			Producto PRO = new Producto();
			try { 
				
				PRO.setNombre(campos[1]);
				PRO.setIdProveedor(Integer.parseInt(campos[2]));
				PRO.setPrecioCompra(Double.parseDouble(campos[3]));
				PRO.setIvaCompra(Double.parseDouble(campos[4]));
				PRO.setPrecioVenta(Double.parseDouble(campos[5]));
				productos.add(PRO);
				linea = BR.readLine();
				saveBD.savepro(PRO);
				
			} catch (Exception e) { //No pudo settear los campos debido a tipo de datos incorrectos o archivo no csv corrupto.
				control=1;
				BR.close();
				return productos;
			}
				
							
				
		 }if (BR != null) {
				BR.close();
		 		}
		
		return productos;
		
	}
	
	@Autowired //Para listar productos en la DB
		private  ProductoRepository repo;
			//Listar Productos en Base de datos
			public List<Producto> listar(){
				//System.out.println("llegó a UploadService Listar ");
				return repo.findAll();	
	
		}

}