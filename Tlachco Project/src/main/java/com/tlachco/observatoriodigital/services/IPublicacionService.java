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

	public List<PublicacionesDTO> findByKeyword(String keyword) throws DataAccessException;
	
	public List<PublicacionesDTO> findAllPublicacionesByPropietario(String propietario) throws DataAccessException;
	
	public List<PublicacionesDTO> findTopThreePublicaciones(Integer id_categoria) throws DataAccessException;

	public List<PublicacionesDTO> findReviewPublicacionesByProfesor(String id_profesor) throws DataAccessException;
	
	public void deletePublicacion(Integer id_publicacion) throws DataAccessException;
}
