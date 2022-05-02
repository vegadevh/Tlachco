package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.CategoriaPublicacion;

public interface ICategoriaPublicacionRepo extends JpaRepository<CategoriaPublicacion, Integer> {

}
