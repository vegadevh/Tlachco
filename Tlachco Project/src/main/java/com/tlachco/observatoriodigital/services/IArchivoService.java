package com.tlachco.observatoriodigital.services;

import java.io.IOException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Archivo;

public interface IArchivoService {
	
	public List<Archivo> findAll() throws DataAccessException;
	
//	public void save(Archivo archivo, MultipartFile file) throws IOException;
	
	public Archivo save(Archivo archivo, MultipartFile file) throws IOException;
	
//	public void delete(Integer id_archivo) throws DataAccessException;
//	
	public Archivo findOne(Integer id_archivo) throws DataAccessException;

}
