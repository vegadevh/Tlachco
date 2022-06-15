package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.tlachco.observatoriodigital.domains.Publicacion;

public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer> {

	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, propietario\r\n"
			+ "	FROM public.publicacion WHERE estado = 'Public' and id_categoria = 1 ;")
	public List<Object[]> findAllNoticias() throws DataAccessException;

	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, propietario\r\n"
			+ "	FROM public.publicacion WHERE estado = 'Public' and id_categoria = 2 ;")
	public List<Object[]> findAllArticulos() throws DataAccessException;

	/*
	 * @Query(value =
	 * "SELECT * FROM publicacion p WHERE (lower(p.titulo) like %:keyword% ;",
	 * nativeQuery = true) public List<Publicacion> findByKeyword(String keyword)
	 * throws DataAccessException; // public List<Publicacion>
	 * findByKeyword(@Param("keyword") String keyword);
	 */

	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, propietario FROM public.publicacion p \r\n"
			+"WHERE (lower(p.titulo) like %:keyword% ) \r\n"
			+ "OR (lower(p.propietario) like %:keyword% );")
	public List<Object[]> findByKeyword(String keyword) throws DataAccessException;
	
	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, estado\r\n"
			+ "	FROM public.publicacion WHERE propietario = :propietario ;")
	public List<Object[]> findAllPublicacionesByPropietario(String propietario) throws DataAccessException;
	
	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, propietario\r\n"
			+ "	FROM public.publicacion WHERE id_categoria = :id_categoria ORDER BY id_publicacion DESC limit 3;")
	public List<Object[]> findTopThreePublicaciones(Integer id_categoria) throws DataAccessException;

}
