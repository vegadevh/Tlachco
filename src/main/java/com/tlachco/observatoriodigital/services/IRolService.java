package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Rol;

public interface IRolService {
	
	public List<Rol> findAll() throws DataAccessException;
	
	public Rol findOne(Integer id_rol) throws DataAccessException;

}
