package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Articulo;
import com.tlachco.observatoriodigital.repositories.IArticuloRepo;

@Service
public class ArticuloServiceImpl implements IArticuloService {
	
	@Autowired
	public IArticuloRepo articuloRepo;

	@Override
	public List<Articulo> findALL() throws DataAccessException {
		return articuloRepo.findAll();
	}

	@Override
	public void save(Articulo articulo) throws DataAccessException {
		articuloRepo.save(articulo);
		
	}

	@Override
	public void delete(Integer articulo) throws DataAccessException {
		Articulo article = articuloRepo.getById(articulo);
		articuloRepo.delete(article);
		
	}

	@Override
	public Articulo findOne(Integer articulo) throws DataAccessException {
		return articuloRepo.findById(articulo).orElse(null);
	}

}
