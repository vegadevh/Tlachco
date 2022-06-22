package com.tlachco.observatoriodigital.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tlachco.observatoriodigital.domains.Usuario;
import com.tlachco.observatoriodigital.domains.Video;

public interface IUsuarioRepo extends JpaRepository<Usuario, String>{
	
	@Query(nativeQuery=true, value="Select * from public.usuario where usuario = ?1")
	Optional <Usuario> findyByUserName(String usuario);
	
	@Query(nativeQuery=true, value="SELECT usuario, password, enabled_u, nombre, apellido, id_rol\r\n"
			+ "	FROM public.usuario WHERE id_rol = 2;")
	public List<Usuario> findTeachers() throws DataAccessException;
	
	@Query(nativeQuery=true, value="Select * from public.usuario where (lower(usuario) like %:criteria% ) OR (lower(nombre) like %:criteria% ) OR (lower(apellido) like %:criteria% )")
	public List<Usuario> findByCriteria(String criteria) throws DataAccessException;

}
