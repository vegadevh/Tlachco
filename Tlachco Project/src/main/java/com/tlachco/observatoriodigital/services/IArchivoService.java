package com.tlachco.observatoriodigital.services;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Archivo;

public interface IArchivoService {
	
	public List<Archivo> findAll() throws DataAccessException;
	
	public Archivo save(MultipartFile file) throws DataAccessException;
	
//	public void delete(Integer id_archivo) throws DataAccessException;
//	
//	public Archivo findOne(Integer id_archivo) throws DataAccessException;

}
