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
	public ICategoriaPublicacionRepo categoriaPublicaionRepo;

	@Override
	public List<CategoriaPublicacion> findAll() throws DataAccessException {
		return categoriaPublicaionRepo.findAll();
	}

	@Override
	public void save(CategoriaPublicacion categoriaArticulo) throws DataAccessException {
		categoriaPublicaionRepo.save(categoriaArticulo);
		
	}

	@Override
	public CategoriaPublicacion findOne(Integer usuario) throws DataAccessException {
		return categoriaPublicaionRepo.findById(usuario).orElse(null);
	}

	@Override
	public CategoriaPublicacion findByCategoria(String categoria) throws DataAccessException {
		return categoriaPublicaionRepo.findByCategoria(categoria);
	}

	@Override
	public void deleteCategoria(Integer id_categoria) throws DataAccessException {
		categoriaPublicaionRepo.deleteCategoria(id_categoria);
		
	}

}
