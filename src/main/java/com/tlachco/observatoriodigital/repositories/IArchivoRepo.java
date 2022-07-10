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

	@Query(nativeQuery = true, value = "SELECT id_archivo, nombre, titulo\r\n"
			+ "FROM public.archivo where id_categoria = 4 AND estado = 'Public';")
	public List<Object[]> findAllInfografia() throws DataAccessException;

	@Query(nativeQuery = true, value = "SELECT id_archivo, titulo\r\n"
			+ "	FROM public.archivo WHERE propietario = :propietario ;")
	public List<Object[]> findMyInfografia(String propietario) throws DataAccessException;

	@Query(nativeQuery = true, value = "SELECT id_archivo, titulo\r\n"
			+ "	FROM public.archivo WHERE profesor = :profesor AND estado = 'Review';")
	public List<Object[]> findStudentInfografia(String profesor) throws DataAccessException;

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM public.archivo\r\n"
			+ "WHERE id_archivo= :id_archivo ;")
	public void deleteArchivo(String id_archivo) throws DataAccessException;

}
