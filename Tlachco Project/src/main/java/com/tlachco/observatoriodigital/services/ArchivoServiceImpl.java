package com.tlachco.observatoriodigital.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tlachco.observatoriodigital.domains.Archivo;
import com.tlachco.observatoriodigital.domains.Video;
import com.tlachco.observatoriodigital.dto.PublicacionesDTO;
import com.tlachco.observatoriodigital.repositories.IArchivoRepo;
import com.tlachco.observatoriodigital.repositories.IVideoRepo;

@Service
public class ArchivoServiceImpl implements IArchivoService {

	@Autowired
	private IArchivoRepo archivoRepo;

//	@Override
//	public void save(Archivo archivo, MultipartFile file) throws IOException {
////		String nombreArchivo = file.getOriginalFilename();
////		Archivo archivo = new Archivo(nombreArchivo.getBytes());
//		String nombre = file.getOriginalFilename();
//		archivo.setNombre(nombre);
//		archivo.setTipo(file.getContentType());
//		archivo.setContenido(file.getBytes());
////		Archivo archivo = new Archivo(UUID.randomUUID().toString(),nombreArchivo.getBytes());
//		archivoRepo.save(archivo);
//	}
	
	

	@Override
	public List<Archivo> findAll() throws DataAccessException {
		return archivoRepo.findAll();
	}

	// @Override
	// public void delete(Integer id_archivo) throws DataAccessException {
	// Archivo archivo = archivoRepo.getById(id_archivo);
	// archivoRepo.delete(archivo);
	//
	// }

	@Override
	public Archivo findOne(String id_archivo) throws DataAccessException {
		return archivoRepo.findById(id_archivo).orElse(null);
	}

	@Override
	public Archivo save(Archivo archivo, MultipartFile file) throws IOException {
//		Archivo archivo = new Archivo(nombreArchivo.getBytes());
		String nombre = file.getOriginalFilename();
		
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		String myDate = strDate.replaceAll(":", "");
		
		try {
			archivo.setId_archivo(myDate+nombre);
			archivo.setTipo(file.getContentType());
			archivo.setNombre(nombre);
			archivo.setContenido(file.getBytes());
			return archivoRepo.save(archivo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public String save2(Archivo archivo, MultipartFile file) throws DataAccessException {
//		Archivo archivo = new Archivo(nombreArchivo.getBytes());
		String nombre = file.getOriginalFilename();
		
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		String myDate = strDate.replaceAll(":", "");
		
		try {
			archivo.setId_archivo(myDate+nombre);
			archivo.setTipo(file.getContentType());
			archivo.setNombre(nombre);
			archivo.setContenido(file.getBytes());
			archivoRepo.save(archivo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return archivo.getId_archivo();
	}

	@Override
	public List<Archivo> findAllInfografia() throws DataAccessException {
		List<Archivo> infografias = archivoRepo.findAllInfografia().stream().map(obj -> {
			Archivo p = new Archivo();
			p.setId_archivo(obj[0].toString());
			p.setNombre(obj[1].toString());
			return p;
		}).collect(Collectors.toList());
		return infografias;
	}

}
