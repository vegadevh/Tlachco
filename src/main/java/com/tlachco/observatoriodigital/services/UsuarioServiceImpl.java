package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.repositories.IUsuarioRepo;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	public IUsuarioRepo userRepo;

	@Override
	public List<Usuario> findAll() throws DataAccessException {
		return userRepo.findAll();
	}

	@Override
	public void save(Usuario usuario) throws DataAccessException {
		userRepo.save(usuario);
		
	}

	@Override
	public void delete(String usuario) throws DataAccessException {
		Usuario user = userRepo.getById(usuario);
		userRepo.delete(user);
		
	}

	@Override
	public Usuario findOne(String usuario) throws DataAccessException {
		return userRepo.findById(usuario).orElse(null);
	}
	
	

}
