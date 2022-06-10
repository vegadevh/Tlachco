package com.tlachco.observatoriodigital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.tlachco.observatoriodigital.domains.Publicacion;

public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer>{
	
	@Query(value="SELECT * FROM publicacion p WHERE (lower(p.titulo) like %:keyword%", nativeQuery=true)
	public  List<Publicacion> findByKeyword(String keyword);
	//public  List<Publicacion> findByKeyword(@Param("keyword") String keyword);

}
