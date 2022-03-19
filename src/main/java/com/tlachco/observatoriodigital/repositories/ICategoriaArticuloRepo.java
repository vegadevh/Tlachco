package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.CategoriaArticulo;

public interface ICategoriaArticuloRepo extends JpaRepository<CategoriaArticulo, Integer> {

}
