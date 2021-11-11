package com.mintic.backweb.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.mintic.backweb.modelo.TipoDocumento;

public interface ITipoDocumento extends CrudRepository<TipoDocumento, Long> {

}
