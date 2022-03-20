package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Articulo;
import com.tlachco.observatoriodigital.domains.CategoriaArticulo;

public interface ICategoriaArticuloService {
	
	public List<CategoriaArticulo> findAll() throws DataAccessException;
	
	public void save(CategoriaArticulo categoriaArticulo) throws DataAccessException;

}
