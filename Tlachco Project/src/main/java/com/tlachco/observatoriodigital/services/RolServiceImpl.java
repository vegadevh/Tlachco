package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Rol;
import com.tlachco.observatoriodigital.repositories.IRolRepo;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	private IRolRepo rolRepo;

	@Override
	public List<Rol> findAll() throws DataAccessException {
		return rolRepo.findAll();
	}
	
	@Override
	public Rol findOne(Integer id_rol) throws DataAccessException {
		return rolRepo.getById(id_rol);
	}

}
