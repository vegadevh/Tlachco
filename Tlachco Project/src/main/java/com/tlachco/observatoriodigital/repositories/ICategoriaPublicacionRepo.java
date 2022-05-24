package com.tlachco.observatoriodigital.repositories;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;

public interface ICategoriaPublicacionRepo extends JpaRepository<CategoriaPublicacion, Integer> {
	
	@Query(nativeQuery = true, value="SELECT categoria.id_categoria, categoria.categoria FROM public.categoria WHERE categoria.categoria = :categoria ;")
	public CategoriaPublicacion findByCategoria(String categoria) throws DataAccessException;

}
