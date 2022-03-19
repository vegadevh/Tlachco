package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.LikeArticulo;

public interface ILikeArticuloRepo extends JpaRepository<LikeArticulo, Integer>{

}
