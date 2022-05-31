package com.tlachco.observatoriodigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tlachco.observatoriodigital.domains.Video;

public interface IVideoRepo extends JpaRepository<Video, Integer> {

}
