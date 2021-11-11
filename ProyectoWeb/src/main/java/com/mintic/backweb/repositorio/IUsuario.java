package com.mintic.backweb.repositorio;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mintic.backweb.modelo.Usuario;

/*
 * Aqui se realizan las opereciones crud   
 *  los parametros son la entidad  y el tipo de datos que se definio como @id en la entidad  
 * 
	el id es long debe ir igual en el crud repository <Usuario, Long>
 * 
 * 
 * */
public interface IUsuario extends CrudRepository<Usuario, Long>{
	
	// JPQL: Se hace la consulta sobre la clase 
	@Query("select count(*) from Usuario as p where p.nombreUsuario= :nombreUsuario and p.password=:password")
	Integer findByNombreUsuarioAndPassword(@Param("nombreUsuario") String nombreUsuario,
			@Param("password") String password);

	
	@Query("select p from Usuario as p where p.nombreUsuario= :nombreUsuario and p.password=:password")
	Usuario findByNameAndPassword(@Param("nombreUsuario") String nombreUsuario,
			@Param("password") String password);
	

}
