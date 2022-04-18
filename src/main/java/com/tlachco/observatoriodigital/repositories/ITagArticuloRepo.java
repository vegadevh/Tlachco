package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.TagArticulo;

public interface ITagArticuloRepo extends JpaRepository<TagArticulo, Integer>{

}