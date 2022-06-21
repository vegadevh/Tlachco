package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.tlachco.observatoriodigital.domains.Comentario;

public interface IComentarioRepo extends JpaRepository<Comentario, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT c.contenido, u.nombre, u.apellido, u.usuario, c.id_comentario\r\n"
			+ "FROM public.publicacion AS p\r\n"
			+ "INNER JOIN public.comentario AS c on p.id_publicacion = c.id_publicacion\r\n"
			+ "INNER JOIN public.usuario AS u on c.usuario = u.usuario\r\n"
			+ "WHERE p.id_publicacion = :id_publicacion \r\n"
			+ "ORDER BY c.id_comentario ASC ;")
	public List<Object[]> findCommentByPublication(Integer id_publicacion) throws DataAccessException;
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value ="DELETE FROM public.comentario\r\n"
			+ "	WHERE id_publicacion = :id_publicacion ;")
	public void eliminarComentario(Integer id_publicacion) throws DataAccessException;

}
