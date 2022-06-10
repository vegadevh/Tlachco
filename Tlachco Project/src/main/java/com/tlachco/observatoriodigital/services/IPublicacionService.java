package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;

public interface IPublicacionService {
	
	public List<Publicacion> findALL() throws DataAccessException;
	
	public void save(Publicacion artuiculo) throws DataAccessException;
	
	public void delete(Integer articulo) throws DataAccessException;
	
	public Publicacion findOne(Integer articulo) throws DataAccessException;
	
	public List<PublicacionesDTO> findAllNoticias() throws DataAccessException;
	
	public List<PublicacionesDTO> findAllArticulos() throws DataAccessException;

}
