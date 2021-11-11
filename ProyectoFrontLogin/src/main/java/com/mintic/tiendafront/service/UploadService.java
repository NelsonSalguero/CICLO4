package com.mintic.tiendafront.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.mintic.tiendafront.controlador.CsvControlador;
import com.mintic.tiendafront.modelo.Producto;
import com.mintic.tiendafront.repository.ProductoRepository;
import com.mintic.tiendafront.service.UploadService;

@Service
@Transactional
public class UploadService {

	
	@Autowired
	CsvControlador saveBD = new CsvControlador();
		
	private final Logger logg = LoggerFactory.getLogger(UploadService.class);
	public List<Producto> save(MultipartFile file) throws IOException {
	
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
				logg.info("Ingreso al pelo" + PRO);
				linea = BR.readLine();
				saveBD.savepro(PRO);
			} catch (Exception e) {
				productos=null;
				throw e;
			}
			
		}

		if (BR != null) {
			logg.info("Ingreso al pelo13(??????)");
			BR.close();
		}
		
		return productos;
		
	}
	
	@Autowired
		private  ProductoRepository repo;
			//Listar Productos
			public List<Producto> listar(){
				//System.out.println("llegó a UploadService Listar ");
				return repo.findAll();	
	
		}

}