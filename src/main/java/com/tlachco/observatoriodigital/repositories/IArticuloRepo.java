package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.Articulo;

public interface IArticuloRepo extends JpaRepository<Articulo, Integer>{

}
