package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tlachco.observatoriodigital.domains.Archivo;

@Repository
public interface IArchivoRepo extends JpaRepository<Archivo, String> {
	
	@Query(nativeQuery = true, value = "SELECT id_archivo, nombre\r\n"
			+ "FROM public.archivo where id_categoria = 4;")
	public List<Object[]> findAllInfografia() throws DataAccessException;
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM public.archivo\r\n"
			+ "WHERE id_archivo= :id_archivo ;")
	public void deleteArchivo(String id_archivo) throws DataAccessException;

}
