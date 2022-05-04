package com.tlachco.observatoriodigital.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.repositories.IArchivoRepo;

@Service
public class ArchivoServiceImpl implements IArchivoService{
	
	@Autowired
	private IArchivoRepo archivoRepo;

	@Override
	public void save(Archivo archivo, MultipartFile file) throws IOException {
//		String nombreArchivo = file.getOriginalFilename();
//		Archivo archivo = new Archivo(nombreArchivo.getBytes());
		archivo.setContenido(file.getBytes());
//		Archivo archivo = new Archivo(UUID.randomUUID().toString(),nombreArchivo.getBytes());
		archivoRepo.save(archivo);
	}

	@Override
	public List<Archivo> findAll() throws DataAccessException {
		return archivoRepo.findAll();
	}

//	@Override
//	public void delete(Integer id_archivo) throws DataAccessException {
//		Archivo archivo = archivoRepo.getById(id_archivo);
//		archivoRepo.delete(archivo);
//		
//	}
//
//	@Override
//	public Archivo findOne(Integer id_archivo) throws DataAccessException {
//		return archivoRepo.findById(id_archivo).orElse(null);
//	}
	
	

}
