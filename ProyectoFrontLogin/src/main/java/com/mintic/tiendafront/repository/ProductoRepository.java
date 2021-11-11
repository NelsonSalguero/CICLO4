package com.mintic.tiendafront.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mintic.tiendafront.modelo.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}

