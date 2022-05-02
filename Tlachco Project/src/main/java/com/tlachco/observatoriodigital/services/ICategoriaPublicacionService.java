package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;

public interface ICategoriaPublicacionService {
	
	public List<CategoriaPublicacion> findAll() throws DataAccessException;
	
	public void save(CategoriaPublicacion categoriaArticulo) throws DataAccessException;

}
