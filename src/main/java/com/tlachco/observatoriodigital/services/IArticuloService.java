package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Articulo;

public interface IArticuloService {
	
	public List<Articulo> findALL() throws DataAccessException;
	
	public void save(Articulo artuiculo) throws DataAccessException;
	
	public void delete(Integer articulo) throws DataAccessException;
	
	public Articulo findOne(Integer articulo) throws DataAccessException;

}
