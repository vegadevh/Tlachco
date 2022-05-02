package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;
import com.tlachco.observatoriodigital.repositories.ICategoriaPublicacionRepo;

@Service
public class CategoriaServiceImpl implements ICategoriaPublicacionService{
	
	@Autowired
	public ICategoriaPublicacionRepo categoriaArticuloRepo;

	@Override
	public List<CategoriaPublicacion> findAll() throws DataAccessException {
		return categoriaArticuloRepo.findAll();
	}

	@Override
	public void save(CategoriaPublicacion categoriaArticulo) throws DataAccessException {
		categoriaArticuloRepo.save(categoriaArticulo);
		
	}

}
