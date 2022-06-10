package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tlachco.observatoriodigital.domains.Publicacion;

public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer>{
	
	@Query(nativeQuery = true, value="SELECT titulo, contenido, fecha_publicacion, propietario\r\n"
			+ "	FROM public.publicacion WHERE estado = 'Public' and id_categoria = 1 ;")
	public List<Object[]> findAllNoticias() throws DataAccessException;
	
	@Query(nativeQuery = true, value="SELECT titulo, contenido, fecha_publicacion, propietario\r\n"
			+ "	FROM public.publicacion WHERE estado = 'Public' and id_categoria = 2 ;")
	public List<Object[]> findAllArticulos() throws DataAccessException;

}
