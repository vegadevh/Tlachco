package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.tlachco.observatoriodigital.domains.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll() throws DataAccessException;
	
	public void save(Usuario usuario) throws DataAccessException;
	
	public void delete(String usuario) throws DataAccessException;
	
	public Usuario findOne(String usuario) throws DataAccessException;

}
