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
import com.tlachco.observatoriodigital.dto.ArchivoDTO;
import com.tlachco.observatoriodigital.repositories.IArchivoRepo;

@Service
public class ArchivoServiceImpl implements IArchivoService {

	@Autowired
	private IArchivoRepo archivoRepo;

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
		// Archivo archivo = new Archivo(nombreArchivo.getBytes());
		String nombre = file.getOriginalFilename();

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		String myDate = strDate.replaceAll(":", "");

		try {
			archivo.setId_archivo(myDate + nombre);
			archivo.setTipo(file.getContentType());
			archivo.setNombre(nombre);
			archivo.setContenido(file.getBytes());
			return archivoRepo.save(archivo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String save2(Archivo archivo, MultipartFile file) throws DataAccessException {
		// Archivo archivo = new Archivo(nombreArchivo.getBytes());
		String nombre = file.getOriginalFilename();

		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		String myDate = strDate.replaceAll(":", "");

		try {
			archivo.setId_archivo(myDate + nombre);
			archivo.setTipo(file.getContentType());
			archivo.setNombre(nombre);
			archivo.setContenido(file.getBytes());
			archivoRepo.save(archivo);
		} catch (Exception e) {
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
			p.setTitulo(obj[2].toString());
			return p;
		}).collect(Collectors.toList());
		return infografias;
	}

	@Override
	public void delete(String id_archivo) throws DataAccessException {
		Archivo archivo = archivoRepo.getById(id_archivo);
		archivoRepo.delete(archivo);

	}

	@Override
	public void deleteArchivo(String id_archivo) throws DataAccessException {
		archivoRepo.deleteArchivo(id_archivo);

	}

	@Override
	public List<ArchivoDTO> findMyInfografia(String propietario) throws DataAccessException {
		List<ArchivoDTO> infografias = archivoRepo.findMyInfografia(propietario).stream().map(obj -> {
			ArchivoDTO p = new ArchivoDTO();
			p.setId_archivo(obj[0].toString());
			p.setTitulo(obj[1].toString());
			return p;
		}).collect(Collectors.toList());
		return infografias;
	}

	@Override
	public List<ArchivoDTO> findStudentInfografia(String profesor) throws DataAccessException {
		List<ArchivoDTO> infografias = archivoRepo.findStudentInfografia(profesor).stream().map(obj -> {
			ArchivoDTO p = new ArchivoDTO();
			p.setId_archivo(obj[0].toString());
			p.setTitulo(obj[1].toString());
			return p;
		}).collect(Collectors.toList());
		return infografias;
	}

	@Override
	public void save3(Archivo archivo) throws IOException {
		archivoRepo.save(archivo);
	}

}
