package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.dto.ComentarioDTO;

public interface IComentarioService {

	public void save(Comentario comentario) throws DataAccessException;

	public void delete(Integer comentario) throws DataAccessException;
	
	public void eliminarComentario(String id_publicacion) throws DataAccessException;
	
	public Comentario findOne(Integer comentario) throws DataAccessException;
	
	public List<ComentarioDTO> findCommentByPublication(Integer id_publicacion) throws DataAccessException;
}
