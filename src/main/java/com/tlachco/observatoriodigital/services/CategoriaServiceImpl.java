package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.CategoriaArticulo;
import com.tlachco.observatoriodigital.repositories.ICategoriaArticuloRepo;

@Service
public class CategoriaServiceImpl implements ICategoriaArticuloService{
	
	@Autowired
	public ICategoriaArticuloRepo categoriaArticuloRepo;

	@Override
	public List<CategoriaArticulo> findAll() throws DataAccessException {
		return categoriaArticuloRepo.findAll();
	}

	@Override
	public void save(CategoriaArticulo categoriaArticulo) throws DataAccessException {
		categoriaArticuloRepo.save(categoriaArticulo);
		
	}

}
