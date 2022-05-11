package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tlachco.observatoriodigital.domains.Archivo;

@Repository
public interface IArchivoRepo extends JpaRepository<Archivo, Integer> {

}
