package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Publicacion;

public interface IPublicacionService {
	
	public List<Publicacion> findALL() throws DataAccessException;
	
	public void save(Publicacion artuiculo) throws DataAccessException;
	
	public void delete(Integer articulo) throws DataAccessException;
	
	public Publicacion findOne(Integer articulo) throws DataAccessException;
	
	
	//Filtros
	
	public  List<Publicacion> findByKeyword(String keyword) throws DataAccessException;

}
