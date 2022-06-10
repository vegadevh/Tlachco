package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;

public interface IPublicacionService {

	public List<Publicacion> findALL() throws DataAccessException;

	public void save(Publicacion articulo) throws DataAccessException;

	public void delete(Integer articulo) throws DataAccessException;

	public Publicacion findOne(Integer articulo) throws DataAccessException;

	public List<PublicacionesDTO> findAllNoticias() throws DataAccessException;

	public List<PublicacionesDTO> findAllArticulos() throws DataAccessException;

	// Filtros

	public List<Publicacion> findByKeyword(String keyword) throws DataAccessException;

}
