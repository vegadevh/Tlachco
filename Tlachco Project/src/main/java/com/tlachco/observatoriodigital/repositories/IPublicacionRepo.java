package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.tlachco.observatoriodigital.domains.Publicacion;

public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer> {

	@Query(nativeQuery = true, value = "SELECT p.id_publicacion, p.titulo, p.contenido, p.fecha_publicacion, p.propietario, u.nombre, u.apellido\r\n"
			+ "FROM public.publicacion AS p INNER JOIN public.usuario AS u ON p.propietario  = u.usuario\r\n"
			+ "WHERE estado = 'Public' and id_categoria = 1 ;")
	public List<Object[]> findAllNoticias() throws DataAccessException;

	@Query(nativeQuery = true, value = "SELECT p.id_publicacion, p.titulo, p.contenido, p.fecha_publicacion, p.propietario, u.nombre, u.apellido\r\n"
			+ "FROM public.publicacion AS p INNER JOIN public.usuario AS u ON p.propietario  = u.usuario\r\n"
			+ "WHERE estado = 'Public' and id_categoria = 2 ;")
	public List<Object[]> findAllArticulos() throws DataAccessException;

	/*
	 * @Query(value =
	 * "SELECT * FROM publicacion p WHERE (lower(p.titulo) like %:keyword% ;",
	 * nativeQuery = true) public List<Publicacion> findByKeyword(String keyword)
	 * throws DataAccessException; // public List<Publicacion>
	 * findByKeyword(@Param("keyword") String keyword);
	 */

	@Query(nativeQuery = true, value = "SELECT p.id_publicacion, p.titulo, p.contenido, p.fecha_publicacion, p.propietario, u.nombre, u.apellido FROM public.publicacion AS p \r\n"
			+ "INNER JOIN public.usuario AS u ON p.propietario  = u.usuario \r\n"
			+"WHERE (lower(p.titulo) like %:keyword% ) \r\n"
			+ "OR (lower(p.propietario) like %:keyword% );")
	public List<Object[]> findByKeyword(String keyword) throws DataAccessException;
	
	
	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, estado\r\n"
			+ "	FROM public.publicacion WHERE propietario = :propietario ;")
	public List<Object[]> findAllPublicacionesByPropietario(String propietario) throws DataAccessException;
	
	@Query(nativeQuery = true, value = "SELECT p.id_publicacion, p.titulo, p.contenido, p.fecha_publicacion, p.propietario, u.nombre, u.apellido\r\n"
			+ "FROM public.publicacion AS p INNER JOIN public.usuario AS u ON p.propietario  = u.usuario\r\n"
			+ "WHERE id_categoria = :id_categoria ORDER BY id_publicacion DESC limit 3 ;")
	public List<Object[]> findTopThreePublicaciones(Integer id_categoria) throws DataAccessException;
	
	@Query(nativeQuery = true, value = "SELECT id_publicacion, titulo, contenido, fecha_publicacion, estado\r\n"
			+ "FROM public.publicacion WHERE profesor = :id_profesor ;")
	public List<Object[]> findReviewPublicacionesByProfesor(String id_profesor) throws DataAccessException;
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM public.publicacion\r\n"
			+ "WHERE id_publicacion= :id_publicacion ;")
	public void deletePublicacion(Integer id_publicacion) throws DataAccessException;

}
