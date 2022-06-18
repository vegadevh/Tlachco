package com.tlachco.observatoriodigital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Comentario;
import com.tlachco.observatoriodigital.dto.ComentarioDTO;
import com.tlachco.observatoriodigital.repositories.IComentarioRepo;

@Service
public class ComentarioServiceImpl implements IComentarioService{

	@Autowired
	public IComentarioRepo comentarioRepo;
	
	@Override
	public void save(Comentario comentario) throws DataAccessException {
		comentarioRepo.save(comentario);
		
	}

	@Override
	public void delete(Integer comentario) throws DataAccessException {
		Comentario comentarioaux = comentarioRepo.getById(comentario);
		comentarioRepo.delete(comentarioaux);
		
		
	}

	@Override
	public Comentario findOne(Integer comentario) throws DataAccessException {
		return comentarioRepo.findById(comentario).orElse(null);
	}

	@Override
	public List<ComentarioDTO> findCommentByPublication(Integer id_publicacion) throws DataAccessException {
		List<ComentarioDTO> listaComentarios = comentarioRepo.findCommentByPublication(id_publicacion).stream().map(obj -> {
			ComentarioDTO p = new ComentarioDTO();
			p.setContenido(obj[0].toString());
			p.setNombre(obj[1].toString());
			p.setApellido(obj[2].toString());
			p.setUsuario(obj[3].toString());
			return p;
		}).collect(Collectors.toList());
		return listaComentarios;
	}

}
