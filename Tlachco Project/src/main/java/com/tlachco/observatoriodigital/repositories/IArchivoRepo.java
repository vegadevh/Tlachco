package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tlachco.observatoriodigital.domains.Archivo;

@Repository
public interface IArchivoRepo extends JpaRepository<Archivo, String> {
	
	@Query(nativeQuery = true, value = "SELECT id_archivo, nombre\r\n"
			+ "FROM public.archivo where id_categoria = 4;")
	public List<Object[]> findAllInfografia() throws DataAccessException;

}
