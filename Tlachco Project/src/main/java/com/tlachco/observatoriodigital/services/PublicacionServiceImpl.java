package com.tlachco.observatoriodigital.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;
import com.tlachco.observatoriodigital.repositories.IPublicacionRepo;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

	@Autowired
	public IPublicacionRepo publicacionRepo;

	@Override
	public List<Publicacion> findALL() throws DataAccessException {
		return publicacionRepo.findAll();
	}

	@Override
	public void save(Publicacion articulo) throws DataAccessException {
		publicacionRepo.save(articulo);

	}

	@Override
	public void delete(Integer articulo) throws DataAccessException {
		Publicacion article = publicacionRepo.getById(articulo);
		publicacionRepo.delete(article);

	}

	@Override
	public Publicacion findOne(Integer articulo) throws DataAccessException {
		return publicacionRepo.findById(articulo).orElse(null);
	}

	@Override
	public List<PublicacionesDTO> findAllNoticias() throws DataAccessException {
		List<PublicacionesDTO> noticias = publicacionRepo.findAllNoticias().stream().map(obj -> {
			PublicacionesDTO p = new PublicacionesDTO();
			p.setTitulo(obj[0].toString());
			p.setContenido(obj[1].toString());
			p.setFecha_publicacion(obj[2].toString());
			p.setPropietario(obj[3].toString());
			return p;
		}).collect(Collectors.toList());
		return noticias;
	}

	@Override
	public List<PublicacionesDTO> findAllArticulos() throws DataAccessException {
		List<PublicacionesDTO> articulos = publicacionRepo.findAllArticulos().stream().map(obj -> {
			PublicacionesDTO p = new PublicacionesDTO();
			p.setTitulo(obj[0].toString());
			p.setContenido(obj[1].toString());
			p.setFecha_publicacion(obj[2].toString());
			p.setPropietario(obj[3].toString());
			return p;
		}).collect(Collectors.toList());
		return articulos;
	}

	@Override
	public List<Publicacion> findByKeyword(String keyword) throws DataAccessException {
		return publicacionRepo.findByKeyword(keyword);
	}

}
