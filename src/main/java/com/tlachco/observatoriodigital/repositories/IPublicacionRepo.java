package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.Publicacion;

public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer>{

}
