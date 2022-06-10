package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Publicacion;
import com.tlachco.observatoriodigital.repositories.IPublicacionRepo;

@Service
public class PublicacionServiceImpl implements IPublicacionService {
	
	@Autowired
	public IPublicacionRepo articuloRepo;

	@Override
	public List<Publicacion> findALL() throws DataAccessException {
		return articuloRepo.findAll();
	}

	@Override
	public void save(Publicacion articulo) throws DataAccessException {
		articuloRepo.save(articulo);
		
	}

	@Override
	public void delete(Integer articulo) throws DataAccessException {
		Publicacion article = articuloRepo.getById(articulo);
		articuloRepo.delete(article);
		
	}

	@Override
	public Publicacion findOne(Integer articulo) throws DataAccessException {
		return articuloRepo.findById(articulo).orElse(null);
	}

	@Override
	public List<Publicacion> findByKeyword(String keyword) throws DataAccessException {
		return articuloRepo.findByKeyword(keyword);
	}

}
